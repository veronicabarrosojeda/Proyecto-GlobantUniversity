package com.carDealership.Application.service;


import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.entity.User;
import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.exception.NotFoundException;
import com.carDealership.Application.mapper.UserMapper;
import com.carDealership.Application.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
        Vehicle vehicle = INSTANCE.vehicleDtoToVehicle(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return INSTANCE.vehicleToDtoVehicle(savedVehicle);
    }

    @Override
    public boolean deleteVehicle(Long idVehicle) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(idVehicle);
        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(idVehicle);
            return true;
        } else {
            throw new NotFoundException(idVehicle);
        }
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Optional<Vehicle> vehicleToUpdate = vehicleRepository.findById(vehicleDTO.getId());
        if (vehicleToUpdate.isPresent()) {
            if (vehicleDTO.getMaker() != null)
                vehicleToUpdate.get().setMaker(vehicleDTO.getMaker());
            if (vehicleDTO.getModel() != null)
                vehicleToUpdate.get().setModel(vehicleDTO.getModel());
            if (vehicleDTO.getPrice() != null)
                if (vehicleDTO.getPrice() != 0)
                    vehicleToUpdate.get().setPrice(vehicleDTO.getPrice());
            if (vehicleDTO.getStock() != null)
                vehicleToUpdate.get().setStock(vehicleDTO.getStock());
            if (vehicleDTO.getColor() != null)
                vehicleToUpdate.get().setColor(vehicleDTO.getColor());
            Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate.get());
            return INSTANCE.vehicleToDtoVehicle(updatedVehicle);
        } else {
            throw new NotFoundException(vehicleDTO.getId());
        }
    }

    @Override
    public Boolean getStockById(Long idVehicle) {
        return vehicleRepository.findById(idVehicle).get().getStock();
    }

    @Override
    public VehicleDTO findById(Long id) {
        Optional<Vehicle> foundVehicle = vehicleRepository.findById(id);
        if (foundVehicle.isPresent()) {
            return INSTANCE.vehicleToDtoVehicle(foundVehicle.get());
        }
        throw new NotFoundException(id);
    }

    @Override
    public List<VehicleDTO> findByModel(String model) {
        List<Vehicle> foundVehicles = vehicleRepository.findByModel(model);
        if (!foundVehicles.isEmpty()) {
            List<VehicleDTO> vehicleDTOS = new ArrayList<>();
            for (Vehicle v : foundVehicles) {
                vehicleDTOS.add(INSTANCE.vehicleToDtoVehicle(v));
            }
            return vehicleDTOS;
        }
        return Collections.emptyList();
    }

}
