package com.example.acidcalculationswebapp.repository;

import com.example.acidcalculationswebapp.entity.Acid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcidRepository extends JpaRepository<Acid, Long> {

}


