package com.appwarehaus.controller;

import com.appwarehaus.entity.Currency;
import com.appwarehaus.payload.CurrencyDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrecyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result addCurrency(@RequestBody CurrencyDto currencyDto){
        return currencyService.addCurrency(currencyDto);
    }

    @GetMapping
    public List<Currency> getAllCurrencys(){
        return currencyService.getAllCurrencys();
    }

    @GetMapping("/{id}")
    public Currency getCurrencyBuId(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }

    @PutMapping("/{id}")
    public Result editCurrencyBuId(@PathVariable Integer id, @RequestBody CurrencyDto currencyDto){
        return currencyService.editCurrencyById(id, currencyDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrencyById(@PathVariable Integer id){
        return currencyService.deleteCurrencyById(id);
    }

}
