package com.appwarehaus.repository;

import com.appwarehaus.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InputProductRepo extends JpaRepository<InputProduct, Integer> {

    List<InputProduct> findAllByProductId(Integer product_id);

    List<InputProduct> findAllByInputId(Integer input_id);
}
