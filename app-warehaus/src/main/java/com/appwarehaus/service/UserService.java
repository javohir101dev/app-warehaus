package com.appwarehaus.service;

import com.appwarehaus.entity.User;
import com.appwarehaus.entity.Warehaus;
import com.appwarehaus.helper.Utils;
import com.appwarehaus.payload.Result;
import com.appwarehaus.payload.UserDto;
import com.appwarehaus.repository.UserRepo;
import com.appwarehaus.repository.WarehausRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    public final UserRepo userRepo;
    public final WarehausRepo warehausRepo;


    public UserService(UserRepo userRepo, WarehausRepo warehausRepo) {
        this.userRepo = userRepo;
        this.warehausRepo = warehausRepo;
    }

    public Result addUser(UserDto userDto){
        if (Utils.isEmptry(userDto)){
            return new Result("Request body should not be emptry", false);
        }
        boolean exists = userRepo.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists){
            return new Result("This phone number is already added", false);
        }

        if (Utils.isEmptry(userDto.getWarehausIds())){
            return new Result("Werehaus name should not be emptry", false);
        }

        Set<Integer> setWerehausIds = userDto.getWarehausIds();
        Set<Warehaus> warehausSet = new HashSet<>();
        for (Integer werehausId : setWerehausIds) {
            Optional<Warehaus> optionalWarehaus = warehausRepo.findById(werehausId);
            if (!optionalWarehaus.isPresent()){
                return new Result("Werehaus is not found", false);
            }
            warehausSet.add(optionalWarehaus.get());
        }



        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setWarehaus(warehausSet);
        String code = "1";
        if (!Utils.isEmptry(userRepo.lastId())){
            code = "" + (userRepo.lastId()+1);
        }
        user.setCode(code);
        userRepo.save(user);
        return new Result("User is added", true);

    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            return null;
        }
        return optionalUser.get();
    }

    public List<User> getUserByWarehausId(Integer warehausId){
        return userRepo.findByWarehausId(warehausId);
    }

    public Result editUserById(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            return new Result("User is not found", false);
        }
        if (Utils.isEmptry(userDto)){
            return new Result("Request body should not be emptry", false);
        }
        boolean exists = userRepo.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists){
            return new Result("This phone number is already added", false);
        }

        if (Utils.isEmptry(userDto.getWarehausIds())){
            return new Result("Werehaus name should not be emptry", false);
        }

        Set<Integer> setWerehausIds = userDto.getWarehausIds();
        Set<Warehaus> warehausSet = new HashSet<>();
        for (Integer werehausId : setWerehausIds) {
            Optional<Warehaus> optionalWarehaus = warehausRepo.findById(werehausId);
            if (!optionalWarehaus.isPresent()){
                return new Result("Werehaus is not found", false);
            }
            warehausSet.add(optionalWarehaus.get());
        }



        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setWarehaus(warehausSet);
        userRepo.save(user);
        return new Result("User is edited", true);
    }

    public Result deleteUserById(Integer id){
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            return new Result("User is not found", false);
        }
        try {
            userRepo.deleteById(id);
            return new Result("User is deleted", true);
        }catch (Exception e){
            return new Result("Error in deleting", false);
        }
    }


}
