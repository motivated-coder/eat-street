package com.skd.entity;

import com.skd.enums.ItemType;
import com.skd.enums.ItemUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "es_menu_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MenuItem {
    @Id
    private UUID id;
    private Long menuItemId;
    private String name;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
    @Enumerated(EnumType.STRING)
    private ItemUnit itemUnit;
    private boolean isAvailable;
    private Long restaurantId;

}
