package com.example.acidcalculationswebapp.repository;

import com.example.acidcalculationswebapp.entity.Concentration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcentrationRepository extends JpaRepository<Concentration, Long> {
}
