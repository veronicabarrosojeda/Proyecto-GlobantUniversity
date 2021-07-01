package com.carDealership.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {
    Long id;
    String maker;
    String model;
    Boolean stock;
    Float price;
    String color;
}
