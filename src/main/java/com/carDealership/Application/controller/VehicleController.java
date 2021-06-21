package com.carDealership.Application.controller;

import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.repository.VehicleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleRepository repository;

    VehicleController(VehicleRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/vehicle")
    List<Vehicle> all() {
        return repository.findAll();
    }
}
