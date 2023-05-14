package com.skd.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;
@Embeddable
@Data
public class MenuItemPK implements Serializable {
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
