package com.prateek.springservice.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.View;
import org.apache.beam.sdk.values.PCollectionView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.transforms.windowing.SlidingWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.joda.time.Duration;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableSchema;
import org.joda.time.Instant;

import java.util.HashSet;

public class PipeLineCreator {

    public void preProcess() throws FileNotFoundException {
        //PelicanCluster targetCluster = getTargetCluster();
        String[] args = new String[10];
        args[0] = prepareArgument("tempLocation", "gs://gcp-data-engineer-188205/staging");
        args[1] = prepareArgument("runner", "DataflowRunner");
        args[2] = prepareArgument("project", "gcp-data-engineer-188205");
        args[3] = prepareArgument("stagingLocation", "gs://gcp-data-engineer-188205/staging");
        args[4] = prepareArgument("credentialPath", "C:\\Users\\jasvindersingh.chugh\\Documents\\My Received Files\\gcp-data-engineer-6f56a059b541.json");
        args[5] = prepareArgument("streaming", "true");
        args[6] = prepareArgument("zone", "");
        args[7] = prepareArgument("autoscalingAlgorithm", "NONE");
   /* args[8] = prepareArgument("numWorkers", ""+(Math.min(maxPipelineWorkers, Math.max(infoObjects.size()/8, minPipelineWorkers))));
    args[9] = prepareArgument("maxNumWorkers", ""+maxPipelineWorkers);*/
        final PelicanPipelineOptions options = PipelineOptionsFactory.fromArgs(args).as(PelicanPipelineOptions.class);

        PipelineOptionsFactory.register(PelicanPipelineOptions.class);
        File credentialsPath = new File("C:\\Users\\jasvindersingh.chugh\\Documents\\My Received Files\\gcp-data-engineer-6f56a059b541.json");
        FileInputStream serviceAccountStream = new FileInputStream(credentialsPath);

        HashSet<String> scopeSet = new HashSet<>();
        scopeSet.add("https://www.googleapis.com/auth/cloud-platform");
        scopeSet.add("https://www.googleapis.com/auth/compute");
        scopeSet.add("https://www.googleapis.com/auth/compute.readonly");
        scopeSet.add("https://www.googleapis.com/auth/userinfo.email");


        Pipeline p = Pipeline.create(options);

        // Hardcoded Topic value
        String topic = "stock_tata_steel";
        String output = "let_us_see.ProfilingResult";

        // INitialise the context

        // Build the table schema for the output table.
        List<TableFieldSchema> fields = new ArrayList<>();
        fields.add(new TableFieldSchema().setName("tablename").setType("STRING"));
        fields.add(new TableFieldSchema().setName("columnName").setType("STRING"));
        fields.add(new TableFieldSchema().setName("ruleType").setType("STRING"));
        fields.add(new TableFieldSchema().setName("Status").setType("BOOL"));
        fields.add(new TableFieldSchema().setName("row").setType("STRING"));
        TableSchema schema = new TableSchema().setFields(fields);

        ProfilingContext profilingContext = new ProfilingContext();
        final PCollectionView<ProfilingContext> transferContextView = p.apply("DB Context SideInput", Create.of(profilingContext).withCoder(SerializableCoder.of(ProfilingContext.class))).apply(View.<ProfilingContext>asSingleton());

        p.apply("GetMessages", PubsubIO.readStrings().fromTopic(topic)).apply("window", Window.into(SlidingWindows.of(Duration.standardMinutes(2)).every(Duration.standardSeconds(30))))
                .apply("WordsPerLine", ParDo.of(new ProfilingParDo(transferContextView)).withSideInputs(transferContextView))
                .apply("WordsInTimeWindow", Sum.integersGlobally().withoutDefaults()) //
                .apply("ToBQRow", ParDo.of(new DoFn<Integer, TableRow>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) throws Exception {
                        TableRow row = new TableRow();
                        row.set("timestamp", Instant.now().toString());
                        row.set("num_words", c.element());
                        c.output(row);
                    }
                })) //
                .apply(BigQueryIO.writeTableRows().to(output)//
                        .withSchema(schema)//
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND)
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED));
        p.run();
        /*BigQueryClientConfig config = new BigQueryClientConfig(propertyFileReader.getBigQueryConnectTimeoutmili(),
                propertyFileReader.getBigQueryReadTimeoutMili(), propertyFileReader.getBigQueryRetrySetMaxAttempts(),
                propertyFileReader.getBigQueryMaxRetryDelayMili(), propertyFileReader.getBigQueryRetryTotalTimeoutMili(),
                propertyFileReader.getBigQueryInitialRetryDelayMili(), propertyFileReader.getBigQueryRetryDelayMultiplier(),
                propertyFileReader.getBigQueryInitialRpcTimeout(), propertyFileReader.getBigQueryRpcTimeoutMultiplier(),
                propertyFileReader.getBigQueryMaxRpcTimeout());
        Credentials credentials = ServiceAccountCredentials.fromStream(serviceAccountStream).createScoped(scopeSet);
        BigQuery bigQuery = createBigQueryClient(credentials, options.getProject(), config);
        for (final PipelineTableInfo info : infoObjects) {
            final String tableName = info.getOutputTableName();
            final String tgtDbName = info.getOutputDbName();
            doCleanUp(bigQuery, tgtDbName, tableName);
        }
        options.setGcpCredential(credentials);
        Pipeline pipeline = Pipeline.create(options);
        final PCollectionView<List<PipelineTableInfo>> tableInfoView = pipeline.apply("Table Info SideInput", Create.of(infoObjects).withCoder(SerializableCoder.of(PipelineTableInfo.class))).apply(View.<PipelineTableInfo>asList());
        final PCollectionView<ProfilingContext> transferContextView = pipeline.apply("DB Context SideInput", Create.of(transferContext).withCoder(SerializableCoder.of(ProfilingContext.class))).apply(View.<ProfilingContext>asSingleton());
        pipeline.begin().apply("Begin Transfer", Create.of("seed"))
                .apply("Prepare Transfer", ParDo.of(new TransferCommandHolderDoFn(tableInfoView)).withSideInputs(tableInfoView))
                .apply("Data Transfer", ParDo.of(new DataTransferTransform(transferContextView)).withSideInputs(transferContextView));
        return pipeline.run().waitUntilFinish();
        if (!infoObjects.isEmpty()) {
            ProfilingContext profilingContext = new ProfilingContext();
            PipelineResult.State result = PipelineLauncher.launch(args, infoObjects, propertyFileReader, profilingContext);
            transferResult.setStatus(PipelineResult.State.DONE == result);
            transferResult.setDescription("Data transfer successful.");*/
        }

    private String prepareArgument(String fieldName, String fieldValue) {
        return String.format("--%s=%s", fieldName, fieldValue);
    }
}


