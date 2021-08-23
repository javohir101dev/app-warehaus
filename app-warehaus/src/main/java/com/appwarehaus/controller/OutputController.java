package com.appwarehaus.controller;

import com.appwarehaus.entity.Output;
import com.appwarehaus.payload.OutputDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.OutputService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/output")
public class OutputController {

    private final OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }

    @GetMapping
    public List<Output> getOutputs(){
        return outputService.getAll();
    }

    @GetMapping("/byWarehausId/{warehausId}")
    public List<Output> getAllOutputsByWarehausId(@PathVariable Integer warehausId){
        return outputService.getOutputsByWarehausId(warehausId);
    }

    @GetMapping("/byCode/{code}")
    public List<Output> getAllOutputsByCode(@PathVariable String code){
        return outputService.getOutputsByCode(code);
    }


    @GetMapping("/bySupplierId/{clientId}")
    public List<Output> getAllOutputsByClientId(@PathVariable Integer clientId){
        return outputService.getOutputsByClientId(clientId);
    }

    @GetMapping("/{id}")
    public Output getOutputBuId(@PathVariable Integer id){
       return outputService.getOutputById(id);
    }

    @PutMapping("/{id}")
    public Result editResultById(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        return outputService.editOutputById(id, outputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputById(@PathVariable Integer id){
        return outputService.deleteResultById(id);
    }







}
