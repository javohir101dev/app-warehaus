package com.appwarehaus.service;

import com.appwarehaus.entity.Supplier;
import com.appwarehaus.payload.Result;
import com.appwarehaus.payload.SupplierDto;
import com.appwarehaus.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepo supplierRepo;
    public Result addSupplier(SupplierDto supplierDto){
        boolean exists = supplierRepo.existsByPhoneNumber(supplierDto.getPhoneNumber());
        if (exists){
            return new Result("This phone number is registred already", false);
        }
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepo.save(supplier);
        return new Result("Suppliert is added", true);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierRepo.findAll();
    }

    public Supplier getSupplierById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (!optionalSupplier.isPresent()){
            return new Supplier();
        }
        return optionalSupplier.get();
    }

    public Result editSupplierById(Integer id, SupplierDto supplierDto){
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (!optionalSupplier.isPresent()){
            return new Result("Suppliert is not found", false);
        }
        boolean exists = supplierRepo.existsByPhoneNumber(supplierDto.getPhoneNumber());
        if (exists){
            return new Result("This phone number is registred already", false);
        }
        Supplier supplier = optionalSupplier.get();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepo.save(supplier);
        return new Result("Suppliert is edited", true);
    }

    public Result deleteSupplierById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (!optionalSupplier.isPresent()){
            return new Result("Suppliert is not found", false);
        }
        try {
            supplierRepo.deleteById(id);
            return new Result("Suppliert is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting this supplier", false);
        }
    }
}
