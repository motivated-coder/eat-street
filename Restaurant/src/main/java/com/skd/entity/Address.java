package com.skd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "es_addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String pincode;
    private String city;
    private String state;
    private String landmark;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Restaurant restaurant;

}
