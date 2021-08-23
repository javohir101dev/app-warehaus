package com.appwarehaus.repository;

import com.appwarehaus.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<Currency, Integer> {
    boolean existsByName(String name);
}
