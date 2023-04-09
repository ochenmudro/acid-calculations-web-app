package com.example.acidcalculationswebapp.repository;

import com.example.acidcalculationswebapp.entity.PeakArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeakAreaRepository extends JpaRepository<PeakArea, Long> {
}
