package com.skd.dto;

import com.skd.entity.CartItem;
import com.skd.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {
    private Long id;
    private Long userId;
    private List<CartItemDTO> cartItems;
    private BigDecimal totalPrice;
}
