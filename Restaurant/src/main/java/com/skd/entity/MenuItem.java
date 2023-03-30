package com.skd.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "es_menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    // getters and setters
}
