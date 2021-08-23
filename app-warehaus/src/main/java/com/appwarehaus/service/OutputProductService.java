package com.appwarehaus.service;

import com.appwarehaus.entity.Output;
import com.appwarehaus.entity.OutputProduct;
import com.appwarehaus.entity.Product;
import com.appwarehaus.helper.Utils;
import com.appwarehaus.payload.OutputProductDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.OutputProductRepo;
import com.appwarehaus.repository.OutputRepo;
import com.appwarehaus.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    private final OutputProductRepo outputProductRepo;
    private final ProductRepo productRepo;
    private final OutputRepo outputRepo;


    public OutputProductService(OutputProductRepo outputProductRepo, ProductRepo productRepo, OutputRepo outputRepo) {
        this.outputProductRepo = outputProductRepo;
        this.productRepo = productRepo;
        this.outputRepo = outputRepo;
    }

    public Result addOutputProduct(OutputProductDto outputProductDto){
        if (Utils.isEmptry(outputProductDto)){
            return new Result("Request body should not be emptry", false);
        }
        Optional<Product> optionalProduct = productRepo.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("Product is not found", false);
        }
        Optional<Output> optionalOutput = outputRepo.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()){
            return new Result("Output is not found", false);
        }

        OutputProduct outputProduct = new OutputProduct();

        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepo.save(outputProduct);
        return new Result("Output product is added", true);
    }

    public List<OutputProduct> getAll(){
        return outputProductRepo.findAll();
    }

    public List<OutputProduct> getByproductId(Integer productId){
        return outputProductRepo.findAllByProductId(productId);
    }

    public List<OutputProduct> getByOutputId(Integer outputId){
        return outputProductRepo.findAllByOutputId(outputId);
    }

    public OutputProduct getById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepo.findById(id);
        return optionalOutputProduct.orElse(null);
    }

    public Result editOutputProduct(Integer id, OutputProductDto outputProductDto){
        if (Utils.isEmptry(outputProductDto)){
            return new Result("Request body should not be emptry", false);
        }
        Optional<OutputProduct> optionalOutputProduct = outputProductRepo.findById(id);
        if (!optionalOutputProduct.isPresent()){
            return new Result("OutputProduct is not found", false);
        }

        Optional<Product> optionalProduct = productRepo.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("Product is not found", false);
        }
        Optional<Output> optionalOutput = outputRepo.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()){
            return new Result("Output is not found", false);
        }

        OutputProduct outputProduct = optionalOutputProduct.get();

        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepo.save(outputProduct);
        return new Result("Output product is edited", true);
    }

    public Result deleteOutputProductById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepo.findById(id);
        if (!optionalOutputProduct.isPresent()){
            return new Result("OutputProduct is not found", false);
        }
        try {
            outputProductRepo.deleteById(id);
            return new Result("OutputProduct is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting OutputProduct", false);
        }
    }




}
