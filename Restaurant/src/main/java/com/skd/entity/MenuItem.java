package com.skd.entity;

import com.skd.enums.ItemType;
import com.skd.enums.ItemUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "es_menu_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
    @Enumerated(EnumType.STRING)
    private ItemUnit itemUnit;
    private boolean isAvailable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    // getters and setters
}
