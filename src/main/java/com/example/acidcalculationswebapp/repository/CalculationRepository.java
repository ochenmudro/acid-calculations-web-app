package com.example.acidcalculationswebapp.repository;

import com.example.acidcalculationswebapp.entity.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    Calculation findByAcidIdAndExperimentId(Long acidId, Long experimentId);
}
