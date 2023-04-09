package com.skd.dto;

import com.skd.enums.ItemType;
import com.skd.enums.ItemUnit;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItemDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String itemType;
    private String itemUnit;
    private boolean isAvailable;
}
