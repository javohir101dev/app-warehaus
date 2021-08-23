package com.appwarehaus.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class InputProductDto {

    private Double amount;

    private Double price;

    private Date expireDate;

    private Integer productId;

    private Integer inputId;

}
