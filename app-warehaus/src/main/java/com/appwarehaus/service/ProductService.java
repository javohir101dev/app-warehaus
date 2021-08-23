package com.appwarehaus.service;

import com.appwarehaus.entity.Attachment;
import com.appwarehaus.entity.Category;
import com.appwarehaus.entity.Measurement;
import com.appwarehaus.entity.Product;
import com.appwarehaus.payload.ProductDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.AttachmentRepository;
import com.appwarehaus.repository.CategoryRepository;
import com.appwarehaus.repository.MeasurementRepository;
import com.appwarehaus.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

//    CREATE

    public Result addProduct(ProductDto productDto) {
        boolean exists = productRepo.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists) {
            return new Result("This product is exist in this category", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("This category is not found", false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new Result("This photo is not found", false);
        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) {
            return new Result("This measurement is not found", false);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(String.valueOf(productRepo.findMaxId() + 1));
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepo.save(product);
        return new Result("Product is added", true);


    }

    public Page<Product> getProducts(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return productRepo.findAllByActiveTrue(pageable);
    }

    public Page<Product> getPruductsByCategoryId(Integer categoryId, int page){
        Pageable pageable = PageRequest.of(page, 10);
        return productRepo.findAllByCategoryIdAndActiveTrue(categoryId, pageable);
    }

    public Product getProductById(Integer id){
        Optional<Product> optionalProduct = productRepo.findByIdAndActiveTrue(id);
        if (!optionalProduct.isPresent()){
            return null;
        }
        return optionalProduct.get();
    }

    public Result editProductById(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (!optionalProduct.isPresent()){
            return new Result("Product is not found", false);
        }
        boolean exists = productRepo.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists) {
            return new Result("This product is exist in this category", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("This category is not found", false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new Result("This photo is not found", false);
        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) {
            return new Result("This measurement is not found", false);
        }

        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepo.save(product);
        return new Result("Product is edited", true);
    }


    public Result deleteProductById(Integer id){
        Optional<Product> optionalProduct = productRepo.findByIdAndActiveTrue(id);
        if (!optionalProduct.isPresent()){
            return new Result("Product is not found", false);
        }
        Product product = optionalProduct.get();
        product.setActive(false);
        productRepo.save(product);
        return new Result("Product is deleted", true);
    }
}
