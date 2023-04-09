package com.skd.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterSearchAddressDTO {
    private Long id;
    private String street;
    private String pincode;
    private String city;
    private String state;
    private String landmark;
}
