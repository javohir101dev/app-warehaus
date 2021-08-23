package com.appwarehaus.service;

import com.appwarehaus.entity.Currency;
import com.appwarehaus.entity.Input;
import com.appwarehaus.entity.Supplier;
import com.appwarehaus.entity.Warehaus;
import com.appwarehaus.helper.Utils;
import com.appwarehaus.payload.InputDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.CurrencyRepo;
import com.appwarehaus.repository.InputRepo;
import com.appwarehaus.repository.SupplierRepo;
import com.appwarehaus.repository.WarehausRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    private final InputRepo inputRepo;
    private final WarehausRepo warehausRepo;
    private final SupplierRepo supplierRepo;
    private final CurrencyRepo currencyRepo;



    @Autowired
    public InputService(InputRepo inputRepo, WarehausRepo warehausRepo, SupplierRepo supplierRepo, CurrencyRepo currencyRepo) {
        this.inputRepo = inputRepo;
        this.warehausRepo = warehausRepo;
        this.supplierRepo = supplierRepo;
        this.currencyRepo = currencyRepo;
    }

    public Result addInput(InputDto inputDto){
        if (Utils.isEmptry(inputDto)){
            return new Result("Request body should not be emptry", false);
        }
        boolean exists = inputRepo.existsByFactureNumber(inputDto.getFactureNumber());
        if (exists){
            return new Result("Input with this factory number is exist", false);
        }
        Optional<Warehaus> optionalWarehaus = warehausRepo.findById(inputDto.getWarehausId());
        if (!optionalWarehaus.isPresent()){
            return new Result("Warehaus is not found", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepo.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()){
            return new Result("Supplier is not found", false);
        }
        Optional<Currency> optionalCurrency = currencyRepo.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("Currency is not found", false);
        }

        Input input = new Input();
        input.setName(inputDto.getName());
        input.setDate(Timestamp.valueOf(LocalDateTime.now()));
        input.setWarehaus(optionalWarehaus.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDto.getFactureNumber());

        String code = "1";
        if (!Utils.isEmptry(inputRepo.getLastId())){
            code = "" + (inputRepo.getLastId() + 1);
        }

        input.setCode(code);

        inputRepo.save(input);

        return new Result("Input is added", true);
    }

    public List<Input> getAll(){
        return inputRepo.findAll();
    }

    public List<Input> getInputsByWarehausId(Integer warehausId){
        return inputRepo.findAllByWarehausId(warehausId);
    }

    public List<Input> getInputsBySupplierId(Integer supplierId){
        return inputRepo.findAllBySupplierId(supplierId);
    }

    public List<Input> getInputsByCode(String code){
        return inputRepo.findAllByCode(code);
    }

    public List<Input> getInputsByUseId(Integer userId){
        return inputRepo.findAllByUserId(userId);
    }

    public Input getInputById(Integer id){
        Optional<Input> optionalInput = inputRepo.findById(id);
        if (!optionalInput.isPresent()){
            return null;
        }
        return optionalInput.get();
    }

    public Result editInputById(Integer id, InputDto inputDto){
        if (Utils.isEmptry(inputDto)){
            return new Result("Request body should not be emptry", false);
        }
        Optional<Input> optionalInput = inputRepo.findById(id);
        if (!optionalInput.isPresent()){
            return new Result("Input is not found", false);
        }
        boolean exists = inputRepo.existsByFactureNumber(inputDto.getFactureNumber());
        if (exists&&!inputDto.getFactureNumber().equals(optionalInput.get().getFactureNumber())){
            return new Result("Input with  factory nuber is exist", false);
        }

        Optional<Warehaus> optionalWarehaus = warehausRepo.findById(inputDto.getWarehausId());
        if (!optionalWarehaus.isPresent()){
            return new Result("Warehaus is not found", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepo.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()){
            return new Result("Supplier is not found", false);
        }
        Optional<Currency> optionalCurrency = currencyRepo.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("Currency is not found", false);
        }

        Input input = optionalInput.get();
        input.setName(inputDto.getName());
        input.setWarehaus(optionalWarehaus.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepo.save(input);

        return new Result("Input is edited", true);
    }

    public Result deleteResultById(Integer id){
        Optional<Input> optionalInput = inputRepo.findById(id);
        if (!optionalInput.isPresent()){
            return new Result("Input is not found", false);
        }
        try {
            inputRepo.deleteById(id);
            return new Result("Input is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting", false);
        }
    }
}
