package com.appwarehaus.repository;

import com.appwarehaus.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    boolean existsByName(String name);
}
