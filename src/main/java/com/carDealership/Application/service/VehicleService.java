package com.carDealership.Application.service;

import com.carDealership.Application.dto.VehicleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {
    VehicleDTO addVehicle(VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);

    boolean deleteVehicle(Long idVehicle);

    List<VehicleDTO> getStockByModel(VehicleDTO vehicleDTO);

    List<VehicleDTO> allVehicles();
}
