package com.carDealership.Application.dto;

import com.carDealership.Application.entity.User;
import com.carDealership.Application.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {
    Long id;
    Date saleDate;
    User Seller;
    User Customer;
    List<Vehicle> soldVehicle;
}
