package com.carDealership.Application.service;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.dto.VehicleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {
    VehicleDTO addVehicle(VehicleDTO vehicleDTO);
    List<VehicleDTO> allVehicles();
}
