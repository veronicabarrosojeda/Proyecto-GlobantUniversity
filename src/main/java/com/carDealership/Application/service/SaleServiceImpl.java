package com.carDealership.Application.service;

import com.carDealership.Application.dto.SaleDTO;
import com.carDealership.Application.entity.Sale;
import com.carDealership.Application.exception.NotFoundException;
import com.carDealership.Application.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.carDealership.Application.mapper.SaleMapper.INSTANCE;

@Service
public class SaleServiceImpl implements SaleService {

    SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {

        this.saleRepository = saleRepository;
    }

    public List<SaleDTO> allSales() {
        List<Sale> allSales = saleRepository.findAll();
        if (!CollectionUtils.isEmpty(allSales)) {
            return INSTANCE.allSalesToDto(allSales());
        }
        return Collections.emptyList();
    }

    public SaleDTO findSaleById(Long id) {
        Optional<Sale> foundSale = saleRepository.findById(id);
        if (foundSale.isPresent()) {
            return INSTANCE.saleToSaleDto(foundSale.get());
        }
        throw new NotFoundException(id);
    }

    public SaleDTO newSale(SaleDTO saleDTO) {
        Sale sale = INSTANCE.saleDtoToSale(saleDTO);
        Sale savedSale = saleRepository.save(sale);
        return INSTANCE.saleToSaleDto(savedSale);
    }

    public SaleDTO updateSale(SaleDTO saleDTO) throws NotFoundException {
        Optional<Sale> saleToUpdate = saleRepository.findById(saleDTO.getId());
        if (saleToUpdate.isPresent()) {
            Sale updateSale = INSTANCE.saleDtoToSale(saleDTO);
            Sale updatedSale = saleRepository.save(updateSale);
            return INSTANCE.saleToSaleDto(updatedSale);
        }
        throw new NotFoundException(saleDTO.getId());
    }

    public boolean deleteSale(Long id) {
        try {
            saleRepository.deleteById(id);
            return true;
        } catch (Exception error) {
            return false;
        }
    }


}
