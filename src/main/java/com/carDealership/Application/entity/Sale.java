package com.carDealership.Application.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Date saleDate;
    private UserType Seller;
    private UserType Customer;
    private List <Vehicle> soldVehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public UserType getSeller() {
        return Seller;
    }

    public void setSeller(UserType seller) {
        Seller = seller;
    }

    public UserType getCustomer() {
        return Customer;
    }

    public void setCustomer(UserType customer) {
        Customer = customer;
    }

    public List<Vehicle> getSoldVehicle() {
        return soldVehicle;
    }

    public void setSoldVehicle(List<Vehicle> soldVehicle) {
        this.soldVehicle = soldVehicle;
    }

}
