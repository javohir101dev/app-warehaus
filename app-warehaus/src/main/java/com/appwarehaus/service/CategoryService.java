package com.appwarehaus.service;

import com.appwarehaus.entity.Category;
import com.appwarehaus.payload.CategoryDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getCategoryParentId()!=null){
            boolean exists = categoryRepository.existsByNameAndCategoryParentId(categoryDto.getName(), categoryDto.getCategoryParentId());
            if (exists){
                return new Result("In this parent category this category is exists", false);
            }
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
            if (!optionalParentCategory.isPresent()){
                return new Result("This parent category is not found", false);
            }
            category.setCategoryParent(optionalParentCategory.get());
        }else {
            boolean exists = categoryRepository.existsByName(categoryDto.getName());
            if (exists){
                return new Result("This category is already exists", false);
            }
        }

        categoryRepository.save(category);
        return new Result("Category is succesfully added", true);
    }

    public List<Category> getCategoryByParentCategoryId(Integer categoryParent_id){
       return categoryRepository.findByCategoryParentId(categoryParent_id);
    }

    public Category gerCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return null;
        }
        return optionalCategory.get();
    }

    public Result editCategoryById(Integer id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Result("Category is not found", false);
        }
        boolean exists = categoryRepository.existsByNameAndCategoryParentId(categoryDto.getName(), categoryDto.getCategoryParentId());
        if (exists){
            return new Result("In this parent category this category is exists", false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getCategoryParentId()!=null){
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
            if (!optionalParentCategory.isPresent()){
                return new Result("This parent category is not found", false);
            }
            category.setCategoryParent(optionalParentCategory.get());
        }else {
            boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
            if (existsByName){
                return new Result("This category is already exists", false);
            }
        }
        categoryRepository.save(category);
        return new Result("Category is succesfully edited", true);
    }

    public  Result deleteCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Result("Category is not found", false);
        }
        try {
            categoryRepository.deleteById(id);
            return new Result("Category is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting", false);
        }
    }

}
