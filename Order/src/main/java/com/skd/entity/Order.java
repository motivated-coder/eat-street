package com.skd.entity;

import com.skd.enums.OrderState;
import com.skd.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "es_orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Order {
    @Id
    @Column(name = "id")
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState orderState;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderType orderType;

    @Column(name = "placed_date")
    private Date placedDate;

    @Column(name = "total_price")
    private BigDecimal total;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
