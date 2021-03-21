package com.tena.formula1.service;

import com.tena.formula1.XlsxExtractor;
import com.tena.formula1.model.Season;
import com.tena.formula1.repository.RacerRepo;
import com.tena.formula1.repository.SeasonRepo;
import com.tena.formula1.repository.TeamRepo;
import com.tena.formula1.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {


    @Autowired
    private SeasonRepo seasonRepo;

    @Autowired
    private RacerRepo racerRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Override
    public Map<String, Integer> getRacerPerformance(String username, int position) {
        List<Season> seasons = seasonRepo.findAll();

        Map<String, Integer> result =  seasons.stream()
                .filter(x->x.getRacer().getName().equals(username) && x.getPlace() == position)
                .collect(Collectors.groupingBy(s -> s.getTeam().getName(),Collectors.summingInt(s -> 1)));

        return result;
    }

    @Override
    public Map<String, Integer> getTeamsPerformance(int position) {
        List<Season> seasons = seasonRepo.findAll();                    //get all data from database

        Map<String, Integer> result = seasons.stream()
                .filter(s -> s.getPlace() == position)
                .collect(Collectors.groupingBy(s -> s.getTeam().getName(),Collectors.summingInt(s -> 1)));


        Map<String, Integer> sorted = result.entrySet().stream().sorted(Map.Entry.comparingByValue())
                                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));

        return sorted;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        XlsxExtractor extractor = new XlsxExtractor();
        extractor.readFIle(racerRepo,teamRepo,seasonRepo);
    }
}
