package com.appwarehaus.service;

import com.appwarehaus.entity.Currency;
import com.appwarehaus.entity.Output;
import com.appwarehaus.entity.Client;
import com.appwarehaus.entity.Warehaus;
import com.appwarehaus.helper.Utils;
import com.appwarehaus.payload.OutputDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.CurrencyRepo;
import com.appwarehaus.repository.OutputRepo;
import com.appwarehaus.repository.ClientRepo;
import com.appwarehaus.repository.WarehausRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    private final OutputRepo outputRepo;
    private final WarehausRepo warehausRepo;
    private final ClientRepo clientRepo;
    private final CurrencyRepo currencyRepo;



    @Autowired
    public OutputService(OutputRepo outputRepo, WarehausRepo warehausRepo, ClientRepo clientRepo, CurrencyRepo currencyRepo) {
        this.outputRepo = outputRepo;
        this.warehausRepo = warehausRepo;
        this.clientRepo = clientRepo;
        this.currencyRepo = currencyRepo;
    }

    public Result addOutput(OutputDto outputDto){
        if (Utils.isEmptry(outputDto)){
            return new Result("Request body should not be emptry", false);
        }
        boolean exists = outputRepo.existsByFactureNumber(outputDto.getFactureNumber());
        if (exists){
            return new Result("Output with this factory number is exists", false);
        }
        Optional<Warehaus> optionalWarehaus = warehausRepo.findById(outputDto.getWarehausId());
        if (!optionalWarehaus.isPresent()){
            return new Result("Warehaus is not found", false);
        }
        Optional<Client> optionalClient = clientRepo.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()){
            return new Result("Client is not found", false);
        }
        Optional<Currency> optionalCurrency = currencyRepo.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("Currency is not found", false);
        }

        Output output = new Output();
        output.setName(outputDto.getName());
        output.setDate(Timestamp.valueOf(LocalDateTime.now()));
        output.setWarehaus(optionalWarehaus.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(outputDto.getFactureNumber());

        String code = "1";
        if (!Utils.isEmptry(outputRepo.getLastId())){
            code = "" + (outputRepo.getLastId() + 1);
        }

        output.setCode(code);

        outputRepo.save(output);

        return new Result("Output is added", true);
    }

    public List<Output> getAll(){
        return outputRepo.findAll();
    }

    public List<Output> getOutputsByWarehausId(Integer warehausId){
        return outputRepo.findAllByWarehausId(warehausId);
    }

    public List<Output> getOutputsByClientId(Integer supplierId){
        return outputRepo.findAllByClientId(supplierId);
    }

    public List<Output> getOutputsByCode(String code){
        return outputRepo.findAllByCode(code);
    }


    public Output getOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepo.findById(id);
        if (!optionalOutput.isPresent()){
            return null;
        }
        return optionalOutput.get();
    }

    public Result editOutputById(Integer id, OutputDto outputDto){
        if (Utils.isEmptry(outputDto)){
            return new Result("Request body should not be emptry", false);
        }
        Optional<Output> optionalOutput = outputRepo.findById(id);
        if (!optionalOutput.isPresent()){
            return new Result("Output is not found", false);
        }
        boolean exists = outputRepo.existsByFactureNumber(outputDto.getFactureNumber());
        if (exists&&!outputDto.getFactureNumber().equals(optionalOutput.get().getFactureNumber())){
            return new Result("Output with  factory number is exists", false);
        }

        Optional<Warehaus> optionalWarehaus = warehausRepo.findById(outputDto.getWarehausId());
        if (!optionalWarehaus.isPresent()){
            return new Result("Warehaus is not found", false);
        }
        Optional<Client> optionalClient = clientRepo.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()){
            return new Result("Client is not found", false);
        }
        Optional<Currency> optionalCurrency = currencyRepo.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("Currency is not found", false);
        }

        Output output = optionalOutput.get();
        output.setName(outputDto.getName());
        output.setWarehaus(optionalWarehaus.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepo.save(output);

        return new Result("Output is edited", true);
    }

    public Result deleteResultById(Integer id){
        Optional<Output> optionalOutput = outputRepo.findById(id);
        if (!optionalOutput.isPresent()){
            return new Result("Output is not found", false);
        }
        try {
            outputRepo.deleteById(id);
            return new Result("Output is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting", false);
        }
    }
}
