package com.appwarehaus.repository;

import com.appwarehaus.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Integer> {

//    select count(*) > 0 from clint c where c.phone_number
    boolean existsByPhoneNumber(String phoneNumber);

}
