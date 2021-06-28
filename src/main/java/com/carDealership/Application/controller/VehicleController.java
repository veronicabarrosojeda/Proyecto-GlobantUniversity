package com.carDealership.Application.controller;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.repository.VehicleRepository;
import com.carDealership.Application.service.UserService;
import com.carDealership.Application.service.VehicleService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    VehicleService vehicleService;

    VehicleController(VehicleService vehicleService) {

        this.vehicleService = vehicleService;
    }

    @PostMapping(path = "vehicle")
    public ResponseEntity<VehicleDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return ok(vehicleService.addVehicle(vehicleDTO));
    }

    @GetMapping(path = "stockvehicles")
    public ResponseEntity<List<VehicleDTO>> getStockVehiclesByModel(@RequestBody VehicleDTO vehicleDTO){
        return ResponseEntity.ok(vehicleService.getStockByModel(vehicleDTO));
    }

    @GetMapping("/")
    public List<VehicleDTO> allVehicles() {
        return vehicleService.allVehicles();
    }
}
