package com.example.acidcalculationswebapp.repository;

import com.example.acidcalculationswebapp.entity.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long> {
    Experiment findByName(String name);
}
