package com.appwarehaus.controller;

import com.appwarehaus.entity.OutputProduct;
import com.appwarehaus.payload.OutputProductDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    private final OutputProductService outputProductService;

    @Autowired
    public OutputProductController(OutputProductService outputService) {
        this.outputProductService = outputService;
    }

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto) {
        return outputProductService.addOutputProduct(outputProductDto);
    }

    @GetMapping
    public List<OutputProduct> getAllOutputProduct() {
        return outputProductService.getAll();
    }

    @GetMapping("/byProductId/{productId}")
    public List<OutputProduct> getByproductId(@PathVariable Integer productId) {
        return outputProductService.getByproductId(productId);
    }

    @GetMapping("/byOutputId/{outputId}")
    public List<OutputProduct> getByOutputId(@PathVariable Integer outputId) {
        return outputProductService.getByOutputId(outputId);
    }

    @GetMapping("/{id}")
    public OutputProduct getById(@PathVariable Integer id) {
        return outputProductService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        return outputProductService.editOutputProduct(id, outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProductById(@PathVariable Integer id) {
        return outputProductService.deleteOutputProductById(id);
    }
}
