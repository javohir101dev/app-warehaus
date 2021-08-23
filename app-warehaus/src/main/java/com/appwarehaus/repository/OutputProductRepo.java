package com.appwarehaus.repository;

import com.appwarehaus.entity.OutputProduct;
import com.appwarehaus.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutputProductRepo extends JpaRepository<OutputProduct, Integer> {

    List<OutputProduct> findAllByProductId(Integer product_id);

    List<OutputProduct> findAllByOutputId(Integer output_id);
}
