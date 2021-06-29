package com.carDealership.Application.service;

import com.carDealership.Application.dto.SaleDTO;

import java.util.List;

public interface SaleService {
    List<SaleDTO> allSales();
    SaleDTO findSaleById(Long id);
    SaleDTO newSale(SaleDTO saleDTO);
    SaleDTO updateSale(SaleDTO saleDTO);
    boolean deleteSale(Long id);

}
