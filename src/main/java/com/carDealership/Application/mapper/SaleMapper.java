package com.carDealership.Application.mapper;

import com.carDealership.Application.dto.SaleDTO;
import com.carDealership.Application.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentmodel = 'spring')
public interface SaleMapper {
    SaleMapper INSTANCE = Mappers.getMapper( SaleMapper.class );

    Sale userDtoToUser(SaleDTO saleDTO);
    SaleDTO userToUserDto(Sale sale);

}
