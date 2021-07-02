package com.carDealership.Application.mapper;

import com.carDealership.Application.dto.SaleDTO;
import com.carDealership.Application.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    Sale saleDtoToSale(SaleDTO saleDTO);

    SaleDTO saleToSaleDto(Sale sale);

}
