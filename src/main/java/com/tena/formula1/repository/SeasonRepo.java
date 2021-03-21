package com.tena.formula1.repository;

import com.tena.formula1.model.Racer;
import com.tena.formula1.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonRepo extends JpaRepository<Season, Long> {

//    @Query(value = "SELECT team.name, count(place) FROM season WHERE racer= ?1 and place= ?2 GROUP BY team")

    List<Season> findByYearAndPlace(int year, int place);
}
