package com.carDealership.Application.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Date saleDate;
    private UserRoleEnum seller;
    private UserRoleEnum customer;
   /* private List <Vehicle> soldVehicle;*/
}
