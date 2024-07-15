package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository.DeliveryEmployeeRepository;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.impl.DeliveryEmployeeServiceImpl;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.Uf;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.VehicleType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Nested
    class CreateEmployee {
        @Test
        void create_newEmployee_successful() {
            var deliveryEmployee = deliveryEmployee();
            when(deliveryEmployeeRepository.save(any(DeliveryEmployee.class))).thenReturn(deliveryEmployee);
            var employee = deliveryEmployeeService.createEmployee(deliveryEmployee);
            verify(deliveryEmployeeRepository, times(1)).save(deliveryEmployee);
            assertThat(employee).isInstanceOf(DeliveryEmployee.class)
                    .isNotNull()
                    .isEqualTo(deliveryEmployee);
        }
    }

    @Nested
    class findEmployee {


        @Test
        void find_employee_successful() {
            var deliveryEmployee = deliveryEmployee();
            when(deliveryEmployeeRepository.findById(anyLong())).thenReturn(Optional.of(deliveryEmployee));
            var employeeFound = deliveryEmployeeService.findEmployee(deliveryEmployee.getId());
            verify(deliveryEmployeeRepository, times(1)).findById(deliveryEmployee.getId());
            assertThat(employeeFound).isEqualTo(deliveryEmployee);
        }

        @Test
        void find_employeeWithOtherId_successful() {
            var deliveryEmployee = deliveryEmployee();
            when(deliveryEmployeeRepository.findById(anyLong())).thenReturn(Optional.empty());
            assertThatThrownBy(() -> deliveryEmployeeService.findEmployee(deliveryEmployee.getId()))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Employee with ID: " + deliveryEmployee.getId() + " not found.");
            verify(deliveryEmployeeRepository, times(1)).findById(deliveryEmployee.getId());
        }

    }


    @Nested
    class UpdateEmployee {

        @Test
        void update_Employee_successful() {
            var deliveryEmployee = deliveryEmployee();
            when(deliveryEmployeeRepository.findById(anyLong())).thenReturn(Optional.of(deliveryEmployee));
            when(deliveryEmployeeRepository.save(any(DeliveryEmployee.class))).thenReturn(deliveryEmployee);
            var employeeFound = deliveryEmployeeService.findEmployee(deliveryEmployee.getId());
            employeeFound.setName("Silva");
            var employeeUpdated = deliveryEmployeeService.alterEmployee(deliveryEmployee.getId(), employeeFound);
            verify(deliveryEmployeeRepository, times(1)).save(any(DeliveryEmployee.class));
            assertThat(employeeUpdated).isInstanceOf(DeliveryEmployee.class)
                    .isNotNull()
                    .extracting(DeliveryEmployee::getName).isEqualTo("Silva");
        }

        @Test
        void update_employeeWrongId_successful() {
            var deliveryEmployee = new DeliveryEmployee(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
            var deliveryEmployee2 = new DeliveryEmployee(2L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);

            when(deliveryEmployeeRepository.findById(anyLong())).thenReturn(Optional.of(deliveryEmployee));
            when(deliveryEmployeeRepository.save(any(DeliveryEmployee.class))).thenReturn(deliveryEmployee);
            assertThatThrownBy(() -> deliveryEmployeeService.alterEmployee(deliveryEmployee.getId(),deliveryEmployee2))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Empregado não apresenta o ID correto");
            verify(deliveryEmployeeRepository, times(1)).findById(deliveryEmployee.getId());
        }
    }

    @Test
    void delete_Employee_successful() {
        var deliveryEmployee = deliveryEmployee();
        doNothing().when(deliveryEmployeeRepository).deleteById(deliveryEmployee.getId());
        deliveryEmployeeService.deleteEmployee(deliveryEmployee.getId());
        verify(deliveryEmployeeRepository, times(1)).deleteById(deliveryEmployee.getId());
    }

    private DeliveryEmployee deliveryEmployee() {
        return new DeliveryEmployee(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
    }
}
