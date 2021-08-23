package com.appwarehaus.controller;

import com.appwarehaus.entity.Supplier;
import com.appwarehaus.payload.Result;
import com.appwarehaus.payload.SupplierDto;
import com.appwarehaus.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplier(@RequestBody SupplierDto supplierDto){
        return supplierService.addSupplier(supplierDto);
    }

    @GetMapping
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier getSupplierBuId(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public Result editSupplierBuId(@PathVariable Integer id, @RequestBody SupplierDto supplierDto){
        return supplierService.editSupplierById(id, supplierDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplierById(@PathVariable Integer id){
        return supplierService.deleteSupplierById(id);
    }

}
