package com.appwarehaus.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class OutputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private Integer outputId;

}
