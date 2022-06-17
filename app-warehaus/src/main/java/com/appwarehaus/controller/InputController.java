package com.appwarehaus.controller;

import com.appwarehaus.entity.Input;
import com.appwarehaus.payload.InputDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/input")
@RequiredArgsConstructor
public class InputController {

    private final InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    @GetMapping
    public List<Input> getInputs(){
        return inputService.getAll();
    }

    @GetMapping("/byWarehausId/{warehausId}")
    public List<Input> getAllInputsByWarehausId(@PathVariable Integer warehausId){
        return inputService.getInputsByWarehouseId(warehausId);
    }

    @GetMapping("/byCode/{code}")
    public List<Input> getAllInputsByCode(@PathVariable String code){
        return inputService.getInputsByCode(code);
    }

    @GetMapping("/byUserId/{userId}")
    public List<Input> getAllInputsByUserId(@PathVariable Integer userId){
        return inputService.getInputsByUseId(userId);
    }

    @GetMapping("/bySupplierId/{supplierId}")
    public List<Input> getAllInputsBySupplierId(@PathVariable Integer supplierId){
        return inputService.getInputsBySupplierId(supplierId);
    }


    @GetMapping("/{id}")
    public Input getInputBuId(@PathVariable Integer id){
       return inputService.getInputById(id);
    }

    @PutMapping("/{id}")
    public Result editResultById(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.editInputById(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputById(@PathVariable Integer id){
        return inputService.deleteResultById(id);
    }







}
