package com.appwarehaus.service;

import com.appwarehaus.entity.Client;
import com.appwarehaus.payload.ClientDto;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.CategoryRepository;
import com.appwarehaus.repository.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    ClientRepo clientRepo;

    public Result addClient(ClientDto clientDto){
        boolean exists = clientRepo.existsByPhoneNumber(clientDto.getPhoneNumber());
        if (exists){
            return new Result("This phone number is registred already", false);
        }
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepo.save(client);
        return new Result("Clirnt is added", true);
    }

    public List<Client> getAllClients(){
        return clientRepo.findAll();
    }

    public Client getClientById(Integer id){
        Optional<Client> optionalClient = clientRepo.findById(id);
        return optionalClient.orElseGet(Client::new);
    }

    public Result editClientById(Integer id, ClientDto clientDto){
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (!optionalClient.isPresent()){
            return new Result("Client is not found", false);
        }
        boolean exists = clientRepo.existsByPhoneNumber(clientDto.getPhoneNumber());
        if (exists){
            return new Result("This phone number is registred already", false);
        }
        Client client = optionalClient.get();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepo.save(client);
        return new Result("Client is edited", true);
    }

    public Result deleteClientById(Integer id){
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (!optionalClient.isPresent()){
            return new Result("Client is not found", false);
        }
        try {
            clientRepo.deleteById(id);
            return new Result("Client is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting this client", false);
        }
    }
}
