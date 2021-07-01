package com.carDealership.Application.service;

import com.carDealership.Application.dto.SaleDTO;
import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.entity.Sale;
import com.carDealership.Application.entity.User;
import com.carDealership.Application.entity.UserRoleEnum;
import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.exception.NotFoundException;
import com.carDealership.Application.mapper.SaleMapper;
import com.carDealership.Application.mapper.UserMapper;
import com.carDealership.Application.mapper.VehicleMapper;
import com.carDealership.Application.repository.SaleRepository;
import com.carDealership.Application.repository.UserRepository;
import com.carDealership.Application.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.carDealership.Application.mapper.SaleMapper.INSTANCE;

@Service
public class SaleServiceImpl implements SaleService {

    SaleRepository saleRepository;
    UserRepository userRepository;
    VehicleRepository vehicleRepository;

    public SaleServiceImpl(SaleRepository saleRepository, UserRepository userRepository,
                           VehicleRepository vehicleRepository) {

        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<SaleDTO> allSales() {
        List<Sale> allSales = saleRepository.findAll();
        if (!CollectionUtils.isEmpty(allSales)) {
            List<SaleDTO> allSalesDTO = new ArrayList<>();

            for (Sale c : allSales) {
                SaleDTO saleDTO = INSTANCE.saleToSaleDto(c);
                VehicleDTO vehicleDTO = VehicleMapper.INSTANCE.vehicleToDtoVehicle(c.getSoldVehicle());
                UserDTO sellerDTO = UserMapper.INSTANCE.userToUserDto(c.getSeller());
                UserDTO customerDTO = UserMapper.INSTANCE.userToUserDto(c.getCustomer());
                saleDTO.setCustomer(customerDTO);
                saleDTO.setSeller(sellerDTO);
                saleDTO.setSoldVehicle(vehicleDTO);
                allSalesDTO.add(INSTANCE.saleToSaleDto(c));
            }
            return allSalesDTO;
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
        Optional<User> optionalSeller = userRepository.findByIdAndRole(saleDTO.getSeller().getId(), UserRoleEnum.SELLER);
        Optional<User> optionalCustomer = userRepository.findByIdAndRole(saleDTO.getCustomer().getId(), UserRoleEnum.CUSTOMER);
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(saleDTO.getSoldVehicle().getId());

        if (optionalSeller.isPresent() && optionalVehicle.isPresent() && optionalCustomer.isPresent()
                && optionalVehicle.get().getStock() == true) {
            User existingSeller = optionalSeller.get();
            User existingCustomer = optionalCustomer.get();
            Vehicle existingVehicle = optionalVehicle.get();
            Sale sale = INSTANCE.saleDtoToSale(saleDTO);
            sale.setSeller(existingSeller);
            sale.setCustomer(existingCustomer);
            sale.setSoldVehicle(existingVehicle);
            Sale savedSale = saleRepository.save(sale);
            return INSTANCE.saleToSaleDto(savedSale);
        }
        return null;
    }

    public SaleDTO updateSale(SaleDTO saleDTO) throws NotFoundException {
        Optional<Sale> saleToUpdate = saleRepository.findById(saleDTO.getId());
        if (saleToUpdate.isPresent()) {
            if (saleDTO.getSaleDate() != null)
                saleToUpdate.get().setSaleDate(saleDTO.getSaleDate());
            if (saleDTO.getCustomer() != null) {
                if (saleDTO.getCustomer().getId() != null) {
                    User customer = userRepository.findById(saleDTO.getCustomer().getId()).get();
                    if (customer != null)
                        saleToUpdate.get().setCustomer(customer);
                }

            }
            if (saleDTO.getSeller() != null) {
                if (saleDTO.getSeller().getId() != null) {
                    User seller = userRepository.findById(saleDTO.getSeller().getId()).get();
                    if (seller != null)
                        saleToUpdate.get().setSeller(seller);
                }
            }
            if (saleDTO.getSoldVehicle() != null) {
                if (saleDTO.getSoldVehicle().getId() != null) {
                    Vehicle v = vehicleRepository.findById(saleDTO.getSoldVehicle().getId()).get();
                    if (v != null) {
                        saleToUpdate.get().setSoldVehicle(v);
                        ;
                    }
                }
            }
            Sale updatedSale = saleRepository.save(saleToUpdate.get());
            return setUserDTOandVehicleDTOtoSaleDTO(updatedSale);
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

    public SaleDTO setUserDTOandVehicleDTOtoSaleDTO(Sale foundSale) {
        VehicleDTO vehicleDTO = VehicleMapper.INSTANCE.vehicleToDtoVehicle(foundSale.getSoldVehicle());
        UserDTO userCustomerDTO = UserMapper.INSTANCE.userToUserDto(foundSale.getCustomer());
        UserDTO userSellerDTO = UserMapper.INSTANCE.userToUserDto(foundSale.getSeller());
        SaleDTO saleDTO = INSTANCE.saleToSaleDto(foundSale);
        saleDTO.setSoldVehicle(vehicleDTO);
        saleDTO.setCustomer(userCustomerDTO);
        saleDTO.setSeller(userSellerDTO);
        return saleDTO;
    }


}
