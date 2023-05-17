package com.skd.entity;

import com.skd.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    private Boolean isOpen;
    @Column(name = "fssai_number")
    private Long fssaiNumber;
    private int totalOrdersReceived;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_state")
    private State state;
    Date registeredDate;
    @Column(name="serving_pincodes")
    private List<Long> servingPincodes;
}
