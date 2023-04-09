package com.skd.dto;

import com.skd.enums.State;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterSearchRestaurantDTO {
    private Long restaurantId;
    private String restaurantName;
    private String phoneNumber;
    private Boolean isOpen;
    private Long fssaiNumber;
    private Integer totalOrdersReceived;
    private State[] restaurantState;

}
