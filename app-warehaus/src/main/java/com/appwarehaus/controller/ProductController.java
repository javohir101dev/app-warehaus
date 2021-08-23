package com.appwarehaus.controller;

import com.appwarehaus.entity.Product;
import com.appwarehaus.payload.ProductDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

//    CREATE
    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

//    READ
    @GetMapping
    public Page<Product> getProducts(@RequestParam int page){
        return productService.getProducts(page);
    }

    @GetMapping("/byCategoryId/{categoryId}")
    public Page<Product> getProductsByCategoryId(@RequestParam int page, @PathVariable Integer categoryId){
        return productService.getPruductsByCategoryId(categoryId,page);
    }

    @GetMapping("/{id}")
    public Product getProductsById( @PathVariable Integer id){
        return productService.getProductById(id);
    }

//    UPDATE
    @PutMapping("/{id}")
    public Result editProductById(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.editProductById(id, productDto);
    }

//    DELETE
    @DeleteMapping("/{id}")
    public Result deleteProductById(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }


}
