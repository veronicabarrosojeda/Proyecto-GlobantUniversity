package com.carDealership.Application.controller;

import com.carDealership.Application.entity.Vehicle;
import com.carDealership.Application.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleRepository vehicleRepository;

    @Test
    public void shouldReturnValidResponseTest(){
        //Arrange
        Mockito.when(vehicleRepository.findAll()).thenReturn(Collections.singletonList( new Vehicle() ));
        //Action
        List <Vehicle> expectedResult = vehicleController.all();
        //Assert
        assertNotNull(expectedResult);
    }

    /*public void shouldThrowsExceptionWhenServiceDownTest(){
        //Arrange
        when(continentService.create(any())).thenThrow(new ServiceException("Service Down"));
        //assert
        Assertions.assertThrows(ServiceException.class, () ->
                continentController.create(ContinentDTO.builder().build())
        );
    }*/
}
