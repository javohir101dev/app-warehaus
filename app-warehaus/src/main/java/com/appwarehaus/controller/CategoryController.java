package com.appwarehaus.controller;

import com.appwarehaus.entity.Category;
import com.appwarehaus.payload.CategoryDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/byParentCategoryId/{parentCatId}")
    public List<Category> getCategoryByParentId(@PathVariable Integer parentCatId){
        return categoryService.getCategoryByParentCategoryId(parentCatId);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.gerCategoryById(id);
    }

    @PutMapping("/{id}")
    public Result ediyCAtegoryById(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategoryById(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public  Result deleteCategoryById(@PathVariable Integer id){
        return categoryService.deleteCategoryById(id);
    }
}
