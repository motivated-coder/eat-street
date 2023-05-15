package com.skd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private Long orderId;
    private String orderState;
    private String orderType;
    private Date placedDate;
    private BigDecimal total;
    private AddressDTO addressDTO;
    private List<OrderItemDTO> orderItemDTOS;
    private Long userId;
    private Long restaurantId;
}
