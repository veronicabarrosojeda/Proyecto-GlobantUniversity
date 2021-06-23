package com.carDealership.Application.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_ROLE")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private UserRoleEnum role;
    private String name;
    private int age;

}
