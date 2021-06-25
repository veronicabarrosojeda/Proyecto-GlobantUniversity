package com.carDealership.Application.service;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.entity.User;
import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.repository.UserRepository;
import com.carDealership.Application.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return Collections.EMPTY_LIST;
    }

    @Override
    public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
        if(vehicleDTO!=null){
            Vehicle vehicle = INSTANCE.vehicleDtoToVehicle(vehicleDTO);
            vehicleRepository.save(vehicle);
        }

        return new VehicleDTO();
    }
}