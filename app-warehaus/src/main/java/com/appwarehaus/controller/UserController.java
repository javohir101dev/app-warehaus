package com.appwarehaus.controller;

import com.appwarehaus.entity.User;
import com.appwarehaus.payload.Result;
import com.appwarehaus.payload.UserDto;
import com.appwarehaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    CREATE
    @PostMapping
    public Result addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/byWarehausId/{warehausId}")
    public List<User> getUsersByWarehausId(@PathVariable Integer warehausId){
        return userService.getUserByWarehausId(warehausId);
    }

    @PutMapping("/{id}")
    public Result editUserById(@PathVariable Integer id, @RequestBody UserDto userDto){
       return userService.editUserById(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }


}
