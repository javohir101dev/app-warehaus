package com.appwarehaus.repository;

import com.appwarehaus.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCategoryParentId(Integer categoryParent_id);

    boolean existsByNameAndCategoryParentId(String name, Integer categoryParent_id);

    boolean existsByName(String name);
}
