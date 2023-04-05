package com.skd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "es_restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;
    private String phoneNumber;
    private boolean isOpen;
    @Column(name = "fssai_number")
    private Long fssaiNumber;
    private int totalOrdersReceived;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems = new ArrayList<>();
    // getters and setters
}
