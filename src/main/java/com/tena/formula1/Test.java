package com.tena.formula1;

import com.tena.formula1.repository.RacerRepo;
import com.tena.formula1.repository.SeasonRepo;
import com.tena.formula1.repository.TeamRepo;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test {

    public Test(RacerRepo racerRepo, TeamRepo teamRepo, SeasonRepo seasonRepo){


        XlsxExtractor extractor = new XlsxExtractor();
        extractor.readFIle(racerRepo,teamRepo,seasonRepo);
    }

}
