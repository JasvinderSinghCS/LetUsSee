package com.adqt.springservice.controller;

import com.adqt.springservice.service.ProfilingExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;

@RestController
public class ProfilingController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProfilingExecutor profilingExecutor ;

    @RequestMapping(method = RequestMethod.GET, value = "/api/profiling/{tableName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void startProfiling(@PathParam("tableName") String tableName) {
        try {
            profilingExecutor.executeProfiling(tableName);
        } catch (FileNotFoundException e) {
            log.error("error in profiling {}",e.getMessage());
        }
    }

}

