package com.carDealership.Application.controller;

import com.carDealership.Application.dto.SaleDTO;
import com.carDealership.Application.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    SaleService saleService;

    SaleController(SaleService saleService) {

        this.saleService = saleService;
    }

    @GetMapping("/")
    ResponseEntity<List<SaleDTO>> allSales() {
        return new ResponseEntity<>(saleService.allSales(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<SaleDTO> oneSale(@PathVariable Long id) {
        return new ResponseEntity<>(saleService.findSaleById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<SaleDTO> newSale(@RequestBody SaleDTO saleDTO) {
        return new ResponseEntity<>(this.saleService.newSale(saleDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    ResponseEntity<SaleDTO> updateUser(@RequestBody SaleDTO saleDTO, @PathVariable Long id) {
        saleDTO.setId(id);
        return new ResponseEntity<>(this.saleService.updateSale(saleDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        boolean ok = this.saleService.deleteSale(id);
        if (ok) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
