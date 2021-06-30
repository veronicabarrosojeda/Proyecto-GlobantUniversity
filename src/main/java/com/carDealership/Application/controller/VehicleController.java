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

    @GetMapping("/getvehicles")
    public ResponseEntity<List<VehicleDTO>> allVehicles() {
        return new ResponseEntity<>(vehicleService.allVehicles(), HttpStatus.OK);
    }

    @GetMapping("/getvehiclesbymodel/{model}")
    public ResponseEntity<List<VehicleDTO>> findByModel(@PathVariable String model) {
        return new ResponseEntity<>(vehicleService.findByModel(model), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> oneVehicle(@PathVariable Long id) {
        return new ResponseEntity<> (vehicleService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/stockbyid/{id}")
    public ResponseEntity<Boolean> stockById(@PathVariable Long id){
        return new ResponseEntity<> ( vehicleService.getStockById(id), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable Long id) {
        vehicleDTO.setId(id);
        return new ResponseEntity<>(vehicleService.updateVehicle(vehicleDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VehicleDTO> deleteVehicle(@PathVariable Long id) {
        boolean ok = vehicleService.deleteVehicle(id);
        if (ok) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
