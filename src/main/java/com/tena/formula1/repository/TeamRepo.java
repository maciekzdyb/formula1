package com.tena.formula1.repository;

import com.tena.formula1.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
}
