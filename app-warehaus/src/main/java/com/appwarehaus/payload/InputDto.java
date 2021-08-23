package com.appwarehaus.payload;

import lombok.Data;

@Data
public class InputDto {

    private String name;

    private Integer warehausId;

    private Integer supplierId;

    private Integer currencyId;

    private String factureNumber;
}
