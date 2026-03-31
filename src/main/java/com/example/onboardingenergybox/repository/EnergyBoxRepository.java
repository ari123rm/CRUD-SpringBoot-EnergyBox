package com.example.onboardingenergybox.repository;

import com.example.onboardingenergybox.model.EnergyBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyBoxRepository extends JpaRepository<EnergyBox, Long> {}