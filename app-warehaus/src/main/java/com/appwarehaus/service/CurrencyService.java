package com.appwarehaus.service;

import com.appwarehaus.entity.Currency;
import com.appwarehaus.payload.CurrencyDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepo currencyRepo;

    public Result addCurrency(CurrencyDto currencyDto){
        boolean exists = currencyRepo.existsByName(currencyDto.getName());
        if (exists){
            return new Result("This currency is registred already", false);
        }
        Currency currency = new Currency();
        currency.setName(currencyDto.getName());
        currencyRepo.save(currency);
        return new Result(" Currency is added", true);
    }

    public List<Currency> getAllCurrencys(){
        return currencyRepo.findAll();
    }

    public Currency getCurrencyById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (!optionalCurrency.isPresent()){
            return new Currency();
        }
        return optionalCurrency.get();
    }

    public Result editCurrencyById(Integer id, CurrencyDto currencyDto){
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (!optionalCurrency.isPresent()){
            return new Result(" Currency is not found", false);
        }
        boolean exists = currencyRepo.existsByName(currencyDto.getName());
        if (exists){
            return new Result("This currency is registred already", false);
        }
        Currency currency = optionalCurrency.get();
        currency.setName(currencyDto.getName());
        currencyRepo.save(currency);
        return new Result(" Currency is edited", true);
    }

    public Result deleteCurrencyById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (!optionalCurrency.isPresent()){
            return new Result(" Currency is not found", false);
        }
        try {
            currencyRepo.deleteById(id);
            return new Result("Currency is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting this currency", false);
        }
    }
}
