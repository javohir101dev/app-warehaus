package com.appwarehaus.repository;

import com.appwarehaus.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InputRepo extends JpaRepository<Input, Integer> {

    boolean existsByFactureNumber(String factureNumber);

    @Query(nativeQuery = true, value = "select max(id) from input")
    Integer getLastId();

    List<Input> findAllByWarehausId(Integer warehaus_id);

    List<Input> findAllBySupplierId(Integer supplier_id);

    List<Input> findAllByCode(String code);

    @Query(nativeQuery = true, value = "select i.id,\n" +
            "       i.code,\n" +
            "       i.date,\n" +
            "       i.facture_number,\n" +
            "       i.name,\n" +
            "       i.currency_id,\n" +
            "       i.supplier_id,\n" +
            "       i.warehaus_id\n" +
            "from input i\n" +
            "         join warehaus w on i.warehaus_id = w.id\n" +
            "         join users_warehaus uw on w.id = uw.warehaus_id\n" +
            "where uw.users_id = :userId")
    List<Input> findAllByUserId(Integer userId);

}
