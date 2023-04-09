package com.skd.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String street;
    private String pincode;
    private String city;
    private String state;
    private String landmark;
}
