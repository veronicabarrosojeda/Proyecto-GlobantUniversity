package com.carDealership.Application.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Date saleDate;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User customer;

    @OneToOne
    private Vehicle soldVehicle;
}
