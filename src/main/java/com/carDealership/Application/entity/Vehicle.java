package com.carDealership.Application.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String maker;
    private String model;
    private boolean stock;
    private float price;
    private String color;

}
