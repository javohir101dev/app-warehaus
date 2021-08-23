package com.appwarehaus.repository;

import com.appwarehaus.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);

    @Query(value = "select max(id) from product", nativeQuery = true)
    Integer findMaxId();

    Page<Product> findAllByCategoryIdAndActiveTrue(Integer category_id, Pageable pageable);

    Page<Product> findAllByActiveTrue(Pageable pageable);

    Optional<Product> findByIdAndActiveTrue(Integer id);
}
