package com.skd.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddressDTO {
    private String street;
    private String pincode;
    private String city;
    private String state;
}
