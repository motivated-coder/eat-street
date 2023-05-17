package com.skd.dto;

import com.skd.entity.Address;
import com.skd.entity.MenuItem;
import com.skd.enums.State;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantDTO {
    private Long id;
    private String name;
    private String description;
    private List<AddressDTO> addresses;
    private String phoneNumber;
    private boolean isOpen;
    private Long fssaiNumber;
    private int totalOrdersReceived;
    private List<MenuItemDTO> menuItems = new ArrayList<>();
    private String state;
    private List<Long> servingPincodes;
}
