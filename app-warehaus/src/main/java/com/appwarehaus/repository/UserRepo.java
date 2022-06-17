package com.appwarehaus.repository;

import com.appwarehaus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query(nativeQuery = true, value = "select max(id) from users")
    Integer lastId();

    @Query(nativeQuery = true, value = "select * from users\n" +
            "join users_warehaus uw on users.id = uw.users_id\n" +
            "where uw.warehaus_id=:warehausId")
    List<User> findByWarehouseId(Integer warehausId);

}
