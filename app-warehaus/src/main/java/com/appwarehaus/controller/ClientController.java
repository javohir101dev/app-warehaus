package com.appwarehaus.controller;

import com.appwarehaus.entity.Client;
import com.appwarehaus.payload.ClientDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public Result addClient(@RequestBody ClientDto clientDto){
        return clientService.addClient(clientDto);
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public Result editClientById(@PathVariable Integer id, @RequestBody ClientDto clientDto){
        return clientService.editClientById(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteClientById(@PathVariable Integer id){
        return clientService.deleteClientById(id);
    }

}
