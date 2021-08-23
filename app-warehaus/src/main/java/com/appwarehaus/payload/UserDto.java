package com.appwarehaus.payload;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    private Set<Integer> warehausIds;

}
