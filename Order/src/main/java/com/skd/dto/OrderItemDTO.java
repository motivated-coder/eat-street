package com.skd.dto;

import com.skd.entity.MenuItem;
import com.skd.entity.Order;
import com.skd.enums.OrderItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

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
