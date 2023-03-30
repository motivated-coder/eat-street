package com.skd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "es_users")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    // getters and setters
}
