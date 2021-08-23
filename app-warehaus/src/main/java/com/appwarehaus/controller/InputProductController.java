package com.appwarehaus.controller;

import com.appwarehaus.entity.InputProduct;
import com.appwarehaus.payload.InputProductDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    private final InputProductService inputProductService;

    @Autowired
    public InputProductController(InputProductService inputService) {
        this.inputProductService = inputService;
    }

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.addInputProduct(inputProductDto);
    }

    @GetMapping
    public List<InputProduct> getAllInputProduct() {
        return inputProductService.getAll();
    }

    @GetMapping("/byProductId/{productId}")
    public List<InputProduct> getByproductId(@PathVariable Integer productId) {
        return inputProductService.getByproductId(productId);
    }

    @GetMapping("/byInputId/{inputId}")
    public List<InputProduct> getByInputId(@PathVariable Integer inputId) {
        return inputProductService.getByInputId(inputId);
    }

    @GetMapping("/{id}")
    public InputProduct getById(@PathVariable Integer id) {
        return inputProductService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.editInputProduct(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProductById(@PathVariable Integer id) {
        return inputProductService.deleteInputProductById(id);
    }
}
