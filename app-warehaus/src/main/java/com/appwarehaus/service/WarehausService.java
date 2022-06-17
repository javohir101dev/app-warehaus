package com.appwarehaus.service;

import com.appwarehaus.entity.Warehouse;
import com.appwarehaus.payload.WarehausDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.WarehausRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehausService {
    @Autowired
    WarehausRepo warehausRepo;

    public Result addWarehaus(WarehausDto warehausDto){
        boolean exists = warehausRepo.existsByName(warehausDto.getName());
        if (exists){
            return new Result("This warehaus is registred already", false);
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehausDto.getName());
        warehausRepo.save(warehouse);
        return new Result(" Warehaus is added", true);
    }

    public List<Warehouse> getAllWarehauss(){
        return warehausRepo.findAll();
    }

    public Warehouse getWarehausById(Integer id){
        Optional<Warehouse> optionalWarehaus = warehausRepo.findById(id);
        if (!optionalWarehaus.isPresent()){
            return new Warehouse();
        }
        return optionalWarehaus.get();
    }

    public Result editWarehausById(Integer id, WarehausDto warehausDto){
        Optional<Warehouse> optionalWarehaus = warehausRepo.findById(id);
        if (!optionalWarehaus.isPresent()){
            return new Result("Warehaus is not found", false);
        }
        boolean exists = warehausRepo.existsByName(warehausDto.getName());
        if (exists){
            return new Result("This warehaus is registred already", false);
        }
        Warehouse warehouse = optionalWarehaus.get();
        warehouse.setName(warehausDto.getName());
        warehausRepo.save(warehouse);
        return new Result(" Warehaus is edited", true);
    }

    public Result deleteWarehausById(Integer id){
        Optional<Warehouse> optionalWarehaus = warehausRepo.findById(id);
        if (!optionalWarehaus.isPresent()){
            return new Result(" Warehaus is not found", false);
        }
        try {
            warehausRepo.deleteById(id);
            return new Result("Warehaus is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting this warehaus", false);
        }
    }
}
