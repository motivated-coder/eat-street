package com.skd.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "es_addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String pincode;
    private String city;
    private String state;

}
