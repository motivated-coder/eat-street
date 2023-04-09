package com.skd.dto;

import com.skd.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemDTO {
    private Long id;
    private MenuItemDTO menuItem;
    private Integer quantity;
}
