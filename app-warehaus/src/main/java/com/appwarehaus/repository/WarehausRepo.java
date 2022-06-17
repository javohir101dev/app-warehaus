package com.appwarehaus.repository;

import com.appwarehaus.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehausRepo extends JpaRepository<Warehouse, Integer> {
    boolean existsByName(String name);
}
