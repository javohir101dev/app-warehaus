package com.appwarehaus.service;

import com.appwarehaus.entity.Input;
import com.appwarehaus.entity.InputProduct;
import com.appwarehaus.entity.Product;
import com.appwarehaus.helper.Utils;
import com.appwarehaus.payload.InputProductDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.InputProductRepo;
import com.appwarehaus.repository.InputRepo;
import com.appwarehaus.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    private final InputProductRepo inputProductRepo;
    private final ProductRepo productRepo;
    private final InputRepo inputRepo;


    public InputProductService(InputProductRepo inputProductRepo, ProductRepo productRepo, InputRepo inputRepo) {
        this.inputProductRepo = inputProductRepo;
        this.productRepo = productRepo;
        this.inputRepo = inputRepo;
    }

    public Result addInputProduct(InputProductDto inputProductDto){
        if (Utils.isEmptry(inputProductDto)){
            return new Result("Request body should not be emptry", false);
        }
        Optional<Product> optionalProduct = productRepo.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("Product is not found", false);
        }
        Optional<Input> optionalInput = inputRepo.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()){
            return new Result("Input is not found", false);
        }

        InputProduct inputProduct = new InputProduct();

        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepo.save(inputProduct);
        return new Result("Input product is added", true);
    }

    public List<InputProduct> getAll(){
        return inputProductRepo.findAll();
    }

    public List<InputProduct> getByproductId(Integer productId){
        return inputProductRepo.findAllByProductId(productId);
    }

    public List<InputProduct> getByInputId(Integer inputId){
        return inputProductRepo.findAllByInputId(inputId);
    }

    public InputProduct getById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepo.findById(id);
        return optionalInputProduct.orElse(null);
    }

    public Result editInputProduct(Integer id, InputProductDto inputProductDto){
        if (Utils.isEmptry(inputProductDto)){
            return new Result("Request body should not be emptry", false);
        }
        Optional<InputProduct> optionalInputProduct = inputProductRepo.findById(id);
        if (!optionalInputProduct.isPresent()){
            return new Result("InputProduct is not found", false);
        }

        Optional<Product> optionalProduct = productRepo.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("Product is not found", false);
        }
        Optional<Input> optionalInput = inputRepo.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()){
            return new Result("Input is not found", false);
        }

        InputProduct inputProduct = optionalInputProduct.get();

        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepo.save(inputProduct);
        return new Result("Input product is edited", true);
    }

    public Result deleteInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepo.findById(id);
        if (!optionalInputProduct.isPresent()){
            return new Result("InputProduct is not found", false);
        }
        try {
            inputProductRepo.deleteById(id);
            return new Result("InputProduct is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting InputProduct", false);
        }
    }




}
