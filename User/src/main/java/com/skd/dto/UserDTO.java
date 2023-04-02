package com.skd.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String roles;
    private String email;
    private String password;
    private List<AddressDTO> address;
    private String phoneNumber;
}
