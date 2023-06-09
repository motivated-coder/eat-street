package com.skd.entity;

import com.skd.enums.OrderItemType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "es_order_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderItemType orderItemType;

    @Column(name="qty")
    @Max(value = 5, message = "cannot place more than 5 items in a single order")
    @NotNull
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_item_id")
//    @JoinColumns({
//            @JoinColumn(name="menu_item_key_id", insertable = false, updatable = false),
//            @JoinColumn(name = "menu_item_key_order_id", insertable = false, updatable = false)
//    }) //A Very Important concept to understand how to refer or use an entity whose pk is of type @EmbeddedId

    private MenuItem menuItem;
}
