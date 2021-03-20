package com.tena.formula1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private SeasonService seasonService;


    @GetMapping("/getRacerPerformance")
    public Map<String, Integer> getRacerPerf(@RequestParam(value ="name", required = true) String name,
                                             @RequestParam(value = "pos", required = true) Integer pos){
        Map<String, Integer> map = seasonService.getRacerPerformance(name,pos);
        return map;
    }

    @GetMapping("/getTeamsPerformance")
    public Map<String, Integer> getTeamsPerf(@RequestParam(value = "pos", required = true) Integer pos){

        Map<String, Integer> map = seasonService.getTeamsPerformance(pos);
        return map;
    }
}
