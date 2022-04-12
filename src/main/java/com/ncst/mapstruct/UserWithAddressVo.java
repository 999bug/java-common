package com.ncst.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserWithAddressVo {

    private String username;
    private Integer sex;
    private String street;
    private Integer zipCode;
    private Integer houseNumber;
    private String description;
}
