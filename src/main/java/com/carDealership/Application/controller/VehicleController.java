package com.carDealership.Application.controller;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.repository.VehicleRepository;
import com.carDealership.Application.service.UserService;
import com.carDealership.Application.service.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    VehicleService vehicleService;

    VehicleController(VehicleService vehicleService) {

        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public List<VehicleDTO> allVehicles() {
        return vehicleService.allVehicles();
    }
}
