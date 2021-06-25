package com.carDealership.Application.mapper;

import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle vehicleDtoToVehicle(VehicleDTO vehicleDTO);
    VehicleDTO vehicleToDtoVehicle(Vehicle vehicle);
    List<VehicleDTO> allVehiclesToDto(List<Vehicle> allVehicles);
}
