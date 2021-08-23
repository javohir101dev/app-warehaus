package com.appwarehaus.payload;

import lombok.Data;

@Data
public class OutputDto {

    private String name;

    private Integer warehausId;

    private Integer clientId;

    private Integer currencyId;

    private String factureNumber;
}
