package com.tena.formula1.repository;

import com.tena.formula1.model.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RacerRepo extends JpaRepository<Racer, Long> {

    Optional<Racer> findByName(String name);
}
