package com.carDealership.Application.controller;

import com.carDealership.Application.dto.VehicleDTO;
import com.carDealership.Application.service.VehicleService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleService vehicleService;

    @Test
    public void shouldReturnValidResponseTest() {
        //Arrange
        Mockito.when(vehicleService.allVehicles()).thenReturn(Collections.singletonList(new VehicleDTO()));
        //Action
        ResponseEntity<List<VehicleDTO>> expectedResult = vehicleController.allVehicles();
        //Assert
        assertNotNull(expectedResult);
    }

    @Test
    public void shouldThrowsExceptionWhenServiceDownTest() {
        //Arrange
        Mockito.when(vehicleService.allVehicles()).thenThrow(new ServiceException("Service Down"));
        //assert
        Assertions.assertThrows(ServiceException.class, () ->
                vehicleController.allVehicles()
        );
    }
}
