package com.carDealership.Application.service;


import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.exception.NotFoundException;
import com.carDealership.Application.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.carDealership.Application.mapper.VehicleMapper.INSTANCE;

@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {

        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleDTO> allVehicles() {
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        if (!CollectionUtils.isEmpty(allVehicles)) {
            return INSTANCE.allVehiclesToDto(allVehicles);
        }
        return Collections.emptyList();
    }

    @Override
    public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDTO != null) {
            Vehicle vehicle = INSTANCE.vehicleDtoToVehicle(vehicleDTO);
            vehicleRepository.save(vehicle);
            return INSTANCE.vehicleToDtoVehicle(vehicle);
        }
        return null;
    }

    @Override
    public boolean deleteVehicle(Long idVehicle) {
        if (idVehicle != null) {
            vehicleRepository.deleteById(idVehicle);
            return true;
        } else {
            throw new NotFoundException(idVehicle);
        }

    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDTO != null) {
            Vehicle vehicle = vehicleRepository.findById(vehicleDTO.getId()).get();
            if (!vehicleDTO.getMaker().isEmpty())
                vehicle.setMaker(vehicleDTO.getMaker());
            if (!vehicleDTO.getModel().isEmpty())
                vehicle.setModel(vehicleDTO.getModel());
            if (!vehicleDTO.getPrice().isNaN())
                vehicle.setPrice(vehicleDTO.getPrice());
            if (!vehicleDTO.getStock())
                vehicle.setStock(vehicleDTO.getStock());
            return INSTANCE.vehicleToDtoVehicle(vehicle);
        } else {
            throw new NotFoundException(vehicleDTO.getId());
        }
    }

    public List<VehicleDTO> getStockByModel(VehicleDTO vehicleDTO) {
        List<VehicleDTO> vehiclesDTO = new ArrayList<>();
        List<Vehicle> vehicles = vehicleRepository.findByModel(vehicleDTO.getModel());
        for (Vehicle v : vehicles) {
            if (v.getStock() == true) {
                vehiclesDTO.add(INSTANCE.vehicleToDtoVehicle(v));
            }
        }
        return vehiclesDTO;
    }


}