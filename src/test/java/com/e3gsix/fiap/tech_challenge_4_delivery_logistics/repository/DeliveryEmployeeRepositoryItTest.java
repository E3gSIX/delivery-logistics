package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.Uf;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.VehicleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class DeliveryEmployeeRepositoryItTest {

    @Autowired
    private DeliveryEmployeeRepository deliveryEmployeeRepository;

    @Test
    void create_newEmployee_successful(){
        var deliveryEmployee = deliveryEmployee();
        var employee = deliveryEmployeeRepository.save(deliveryEmployee);
        assertThat(employee).isInstanceOf(DeliveryEmployee.class).isNotNull();
        assertThat(employee.getName()).isEqualTo(deliveryEmployee.getName());
        assertThat(employee.getUf()).isEqualTo(deliveryEmployee.getUf());
        assertThat(employee.getVehicleType()).isEqualTo(deliveryEmployee.getVehicleType());
    }

    @Test
    void find_newEmployee_successful(){
        var employee = deliveryEmployeeRepository.save(deliveryEmployee());
        var employeeOptional = deliveryEmployeeRepository.findById(employee.getId());
        assertThat(employeeOptional)
                .isPresent()
                .containsSame(employee);
    }

    @Test
    void update_Employee_sucessful(){
        var employee = deliveryEmployeeRepository.save(deliveryEmployee());
        employee.setVehicleType(VehicleType.BIKE);
        var employeeUpdated = deliveryEmployeeRepository.save(employee);
        assertThat(employeeUpdated).isInstanceOf(DeliveryEmployee.class)
                .isNotNull();
        assertThat(employeeUpdated.getVehicleType()).isNotEqualTo(VehicleType.CAR);
    }

    @Test
    void delete_Employee_sucessful(){
        var deliveryEmployee = deliveryEmployee();
        var employee = deliveryEmployeeRepository.save(deliveryEmployee());
        deliveryEmployeeRepository.deleteById(deliveryEmployee.getId());
        assertThat(deliveryEmployeeRepository.findById(employee.getId())).isEmpty();
    }

    private DeliveryEmployee deliveryEmployee() {
        return new DeliveryEmployee(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
    }

}
