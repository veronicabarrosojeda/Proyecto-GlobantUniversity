package com.carDealership.Application.controller;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.repository.VehicleRepository;
import com.carDealership.Application.service.UserService;
import com.carDealership.Application.service.VehicleService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
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

    @PostMapping(path = "addvehicle")
    public ResponseEntity<VehicleDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return new ResponseEntity<>(vehicleService.addVehicle(vehicleDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "stockvehicles")
    public ResponseEntity<List<VehicleDTO>> getStockVehiclesByModel(@RequestBody VehicleDTO vehicleDTO){
        return ResponseEntity.ok(vehicleService.getStockByModel(vehicleDTO));
    }

    @GetMapping("/getvehicles")
    public List<VehicleDTO> allVehicles() {
        return vehicleService.allVehicles();
    }

    @PatchMapping(path = "updatevehicle")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return ok(vehicleService.updateVehicle(vehicleDTO));
    }

    @DeleteMapping(path = "deletevehicle")
    public ResponseEntity<VehicleDTO> deleteVehicle(@PathVariable Long idVehicle) {
        boolean ok = vehicleService.deleteVehicle(idVehicle);
        if (ok) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
