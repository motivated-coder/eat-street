package com.skd.entity;

import com.skd.enums.OrderItemType;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "es_order_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OrderItem {
    @Id
    @Column(name = "id")
    private UUID orderItemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "name")
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private OrderItemType orderItemType;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="qty")
    @Max(value = 5, message = "cannot place more than 5 items in a single order")
    @NotNull
    private Integer quantity;
}
