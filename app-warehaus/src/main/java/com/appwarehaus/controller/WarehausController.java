package com.appwarehaus.controller;

import com.appwarehaus.entity.Warehouse;
import com.appwarehaus.payload.WarehausDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.WarehausService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehaus")
public class WarehausController {
    @Autowired
    WarehausService warehausService;

    @PostMapping
    public Result addWarehaus(@RequestBody WarehausDto warehausDto){
        return warehausService.addWarehaus(warehausDto);
    }

    @GetMapping
    public List<Warehouse> getAllWarehauss(){
        return warehausService.getAllWarehauss();
    }

    @GetMapping("/{id}")
    public Warehouse getWarehausBuId(@PathVariable Integer id){
        return warehausService.getWarehausById(id);
    }

    @PutMapping("/{id}")
    public Result editWarehausBuId(@PathVariable Integer id, @RequestBody WarehausDto warehausDto){
        return warehausService.editWarehausById(id, warehausDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehausById(@PathVariable Integer id){
        return warehausService.deleteWarehausById(id);
    }

}
