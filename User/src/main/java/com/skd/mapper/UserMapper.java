package com.skd.mapper;

import com.skd.dto.AddressDTO;
import com.skd.dto.UserDTO;
import com.skd.entity.Address;
import com.skd.entity.UserInfo;
import com.skd.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    public UserInfo toUserInfo(UserDTO userDTO){
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDTO,userInfo);
        userInfo.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userInfo.setRoles(toRoles(userDTO));
        userInfo.setAddresses(toAddress(userDTO));
        userInfo.getAddresses().stream().forEach(address -> address.setUser(userInfo));
        return userInfo;
    }

    private List<Role> toRoles(UserDTO userDTO) {
        List<String> strings = Arrays.stream(userDTO.getRoles().split(",")).collect(Collectors.toList());
        return strings.stream().map(s -> Role.valueOf(s)).collect(Collectors.toList());
    }

    private List<Address> toAddress(UserDTO userDTO) {
       List<Address> addresses = userDTO.getAddress()
               .stream().map(addressDTO -> Address.builder()
                       .state(addressDTO.getState())
                       .pincode(addressDTO.getPincode())
                       .city(addressDTO.getCity())
                       .street(addressDTO.getStreet())
                       .build()).collect(Collectors.toList());
       return addresses;
    }

    public UserDTO toUserDTO(UserInfo userInfo){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo,userDTO);
        userDTO.setPassword(userDTO.getPassword());
        userDTO.setAddress(toAddressDTO(userInfo));
        return userDTO;
    }

    private List<AddressDTO> toAddressDTO(UserInfo userInfo) {
        List<AddressDTO> addresses = userInfo.getAddresses()
                .stream().map(address -> AddressDTO.builder()
                        .state(address.getState())
                        .pincode(address.getPincode())
                        .city(address.getCity())
                        .street(address.getStreet())
                        .build()).collect(Collectors.toList());
        return addresses;
    }

}
