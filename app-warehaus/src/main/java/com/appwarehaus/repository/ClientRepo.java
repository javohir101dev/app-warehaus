package com.appwarehaus.repository;

import com.appwarehaus.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
