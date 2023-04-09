package com.example.acidcalculationswebapp.repository;

import com.example.acidcalculationswebapp.entity.Chromatogram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChromatogramRepository extends JpaRepository<Chromatogram, Long> {
}
