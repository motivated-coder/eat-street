package com.skd.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItemDTO {
    private UUID id;
    private Long menuItemId;
    private String name;
    private String description;
    private BigDecimal price;
    private String itemType;
    private String itemUnit;
    private Long restaurantId;
}
