package com.skd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {
    private Long orderItemId;
    private String orderItemType;
    private Integer quantity;
    private MenuItemDTO menuItemDTO;
}
