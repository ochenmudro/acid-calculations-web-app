package com.example.acidcalculationswebapp.repository;

import com.example.acidcalculationswebapp.entity.Concentration;
import com.example.acidcalculationswebapp.entity.PeakArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeakAreaRepository extends JpaRepository<PeakArea, Long> {

    List<PeakArea> findByChromatogramId(Long chromatogramId);
}
