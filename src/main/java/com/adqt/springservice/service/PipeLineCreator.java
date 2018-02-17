package com.adqt.springservice.service;

import com.adqt.springservice.entity.RuleValue;
import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableSchema;
import com.google.cloud.dataflow.sdk.values.TupleTag;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.windowing.SlidingWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.PCollectionView;
import org.apache.beam.sdk.values.TupleTagList;
import org.joda.time.Duration;
import org.joda.time.Instant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PipeLineCreator {

    public void preProcess(String tableName, Schema schema, List<RuleValue> rules) throws FileNotFoundException {
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

        // Build the table bQSchema for the output table.
        List<TableFieldSchema> fields = new ArrayList<>();
        fields.add(new TableFieldSchema().setName("tablename").setType("STRING"));
        fields.add(new TableFieldSchema().setName("ruleType").setType("STRING"));
        fields.add(new TableFieldSchema().setName("status").setType("BOOL"));
        fields.add(new TableFieldSchema().setName("row").setType("STRING"));
        TableSchema bQSchema = new TableSchema().setFields(fields);

        ProfilingContext profilingContext = new ProfilingContext(tableName,schema,rules);
        TupleTagList tupleTagList = TupleTagList.empty();
        final TupleTag<TableRow> accuracyTag = new TupleTag<TableRow>(){};
        final TupleTag<TableRow> completenessTag = new TupleTag<TableRow>(){};
        final TupleTag<TableRow> conformityTag = new TupleTag<TableRow>(){};
        final TupleTag<TableRow> consistencyTag = new TupleTag<TableRow>(){};

        final PCollectionView<ProfilingContext> transferContextView = p.apply("DB Context SideInput", Create.of(profilingContext).withCoder(SerializableCoder.of(ProfilingContext.class))).apply(View.<ProfilingContext>asSingleton());

        p.apply("GetMessages", PubsubIO.readStrings().fromTopic(topic))
        .apply("window", Window.into(SlidingWindows.of(Duration.standardMinutes(2)).every(Duration.standardSeconds(30))))
        .apply("ProfilingParDo", ParDo.of(new ProfilingParDo(transferContextView))
        .withSideInputs(transferContextView))
        .apply(BigQueryIO.writeTableRows()
          .to(output)
          .withSchema(bQSchema)
          .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND)
          .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED));
        PipelineResult pipelineResult = p.run();
        }

    private String prepareArgument(String fieldName, String fieldValue) {
        return String.format("--%s=%s", fieldName, fieldValue);
    }
}


