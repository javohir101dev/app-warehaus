package com.appwarehaus.repository;

import com.appwarehaus.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
