package com.carDealership.Application.service;

import com.carDealership.Application.dto.VehicleDTO;
import java.util.List;

public interface VehicleService {

    VehicleDTO addVehicle(VehicleDTO vehicleDTO);
    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);
    boolean deleteVehicle(Long idVehicle);
    Boolean getStockById(Long idVehicle);
    List<VehicleDTO> findByModel(String model);
    VehicleDTO findById(Long idVehicle);
    List<VehicleDTO> allVehicles();
}
