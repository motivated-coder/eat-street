package com.skd.dto;

import com.skd.enums.ItemType;
import com.skd.enums.ItemUnit;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterSearchMenuItemDTO {
    private Long menuItemId;
    private String menuName;
    private String description;
    private BigDecimal price;
    private ItemType[] itemType;
    private ItemUnit[] itemUnit;
    private Boolean isAvailable;
}
