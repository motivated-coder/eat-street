package com.skd.entity;

import com.skd.entity.Order;
import com.skd.entity.UserInfo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_history")
public class UserOrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private LocalDateTime orderTime;
    // getters and setters
}
