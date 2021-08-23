package com.appwarehaus.repository;

import com.appwarehaus.entity.Warehaus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehausRepo extends JpaRepository<Warehaus, Integer> {
    boolean existsByName(String name);
}
