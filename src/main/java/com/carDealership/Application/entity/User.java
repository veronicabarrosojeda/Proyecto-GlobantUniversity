package com.carDealership.Application.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "seller" , cascade = CascadeType.ALL)
    List<Sale> sales;
    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    List<Sale> purchases;
}
