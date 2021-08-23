package com.appwarehaus.repository;

import com.appwarehaus.entity.Input;
import com.appwarehaus.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutputRepo extends JpaRepository<Output, Integer> {

    boolean existsByFactureNumber(String factureNumber);

    @Query(nativeQuery = true, value = "select max(id) from output")
    Integer getLastId();

    List<Output> findAllByWarehausId(Integer warehaus_id);

    List<Output> findAllByClientId(Integer client_id);

    List<Output> findAllByCode(String code);
}
