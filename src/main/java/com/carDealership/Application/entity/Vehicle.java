package com.carDealership.Application.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "VEHICLE")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String maker;
    private String model;
    private Boolean stock;
    private Float price;
    private String color;

}
