package com.adqt.springservice.service;

import com.google.api.services.bigquery.model.TableRow;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollectionView;

public class ProfilingParDo extends DoFn<String, KV<String, String>> {

    private final PCollectionView<ProfilingContext> profilingContext;

    public ProfilingParDo(PCollectionView<ProfilingContext> profilingContext) {
        this.profilingContext = profilingContext;
    }

    @ProcessElement
    public void processElement(ProcessContext c) {
         ProfilingContext localProfilingContext = c.sideInput(profilingContext);
         String row = c.element();

         String[] columns = row.split(",");

         for(String column : columns){
             TableRow row = new TableRow();

         }

         }
        for (int i=0; i<tableInfos.size(); i++) {
            final PipelineTableInfo info = tableInfos.get(i);
            com.google.cloud.bigquery.Schema nzToBqSchema = info.getOutputTableSchema();
            final String connectionStr = info.getConnectionString();
            final String query = info.getInputQuery();
            final String tableName = info.getOutputTableName();
            final String tgtDbName = info.getOutputDbName();


            c.output(KV.of(tgtDbName+"_"+tableName+"_"+(i+1)+".csv", transferCommandHolder));
        }
}
