package com.ncst.mapstruct;

import lombok.Data;

@Data
public class Address {
    private String street;
    private Integer zipCode;
    private Integer houseNo;
    private String description;
}
