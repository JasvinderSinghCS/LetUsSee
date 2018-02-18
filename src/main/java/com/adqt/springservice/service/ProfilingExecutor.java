package com.adqt.springservice.service;

import com.adqt.springservice.entity.ColumnInformation;
import com.adqt.springservice.entity.RuleValue;
import com.adqt.springservice.entity.TableInformation;
import com.adqt.springservice.repo.ColumnRepository;
import com.adqt.springservice.repo.RuleValueRepository;
import com.adqt.springservice.repo.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class ProfilingExecutor {

    @Autowired
    RuleValueRepository ruleValueRepository;

    @Autowired
    PipeLineCreator pipeLineCreator;

    @Autowired
    TableRepository tableRepository;

    
    @Autowired
    DataQualityStatService dataQualityStatService;


    public void executeProfiling(String tableName) throws FileNotFoundException {
        TableInformation table = tableRepository.findByTableName(tableName);
        List<RuleValue> rules = ruleValueRepository.findByTableName(tableName);
        Schema schema = new Schema(table.getColumnInformations());
        dataQualityStatService.lauchDataQuality(tableName);
        pipeLineCreator.preProcess(tableName,schema,rules);
    }
}
