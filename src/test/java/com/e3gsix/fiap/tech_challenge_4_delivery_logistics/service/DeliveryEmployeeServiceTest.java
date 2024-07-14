package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository.DeliveryEmployeeRepository;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.impl.DeliveryEmployeeServiceImpl;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.Uf;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.VehicleType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeliveryEmployeeServiceTest {

    private DeliveryEmployeeService deliveryEmployeeService;
    @Mock
    private DeliveryEmployeeRepository deliveryEmployeeRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        deliveryEmployeeService = new DeliveryEmployeeServiceImpl(deliveryEmployeeRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void create_newEmployee_successful(){
        var deliveryEmployee = deliveryEmployee();
        when(deliveryEmployeeRepository.save(any(DeliveryEmployee.class))).thenReturn(deliveryEmployee);
        var employee = deliveryEmployeeService.createEmployee(deliveryEmployee);
        verify(deliveryEmployeeRepository, times(1)).save(deliveryEmployee);
        assertThat(employee).isInstanceOf(DeliveryEmployee.class)
                .isNotNull()
                .isEqualTo(deliveryEmployee);
    }

    @Test
    void find_newEmployee_successful(){
        var deliveryEmployee = deliveryEmployee();
        when(deliveryEmployeeRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(deliveryEmployee));
        var employeeOptional = deliveryEmployeeService.findEmployee(deliveryEmployee.getId());
        verify(deliveryEmployeeRepository, times(1)).findById(deliveryEmployee.getId());
        assertThat(employeeOptional).isEqualTo(deliveryEmployee);
    }

    @Test
    void update_Employee_sucessful(){
        var deliveryEmployee = deliveryEmployee();
        when(deliveryEmployeeRepository.save(any(DeliveryEmployee.class))).thenReturn(deliveryEmployee);
        var employee = deliveryEmployeeService.findEmployee(deliveryEmployee.getId());
        employee.setName("Silva");
        var employeeUpdated = deliveryEmployeeService.alterEmployee(deliveryEmployee.getId(),employee);
        verify(deliveryEmployeeRepository, times(2)).save(any(DeliveryEmployee.class));
        assertThat(employeeUpdated).isInstanceOf(DeliveryEmployee.class)
                .isNotNull()
                .extracting(DeliveryEmployee::getName).isEqualTo("Silva");
    }

    @Test
    void delete_Employee_sucessful(){
        var deliveryEmployee = deliveryEmployee();
        doNothing().when(deliveryEmployeeRepository).deleteById(deliveryEmployee.getId());
        deliveryEmployeeRepository.deleteById(deliveryEmployee.getId());
        verify(deliveryEmployeeRepository, times(1)).deleteById(deliveryEmployee.getId());
    }

    private DeliveryEmployee deliveryEmployee() {
        return new DeliveryEmployee(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
    }
}
