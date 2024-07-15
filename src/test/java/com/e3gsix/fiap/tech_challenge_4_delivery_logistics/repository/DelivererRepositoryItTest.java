package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.Uf;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.VehicleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class DelivererRepositoryItTest {

    @Autowired
    private DelivererRepository delivererRepository;

    @Test
    void create_newEmployee_successful(){
        var deliverer = deliverer();
        var employee = delivererRepository.save(deliverer);
        assertThat(employee).isInstanceOf(Deliverer.class).isNotNull();
        assertThat(employee.getName()).isEqualTo(deliverer.getName());
        assertThat(employee.getUf()).isEqualTo(deliverer.getUf());
        assertThat(employee.getVehicleType()).isEqualTo(deliverer.getVehicleType());
    }

    @Test
    void find_newEmployee_successful(){
        var employee = delivererRepository.save(deliverer());
        var employeeOptional = delivererRepository.findById(employee.getId());
        assertThat(employeeOptional)
                .isPresent()
                .containsSame(employee);
    }

    @Test
    void update_Employee_sucessful(){
        var employee = delivererRepository.save(deliverer());
        employee.setVehicleType(VehicleType.BIKE);
        var employeeUpdated = delivererRepository.save(employee);
        assertThat(employeeUpdated).isInstanceOf(Deliverer.class)
                .isNotNull();
        assertThat(employeeUpdated.getVehicleType()).isNotEqualTo(VehicleType.CAR);
    }

    @Test
    void delete_Employee_sucessful(){
        var deliverer = deliverer();
        var employee = delivererRepository.save(deliverer());
        delivererRepository.deleteById(deliverer.getId());
        assertThat(delivererRepository.findById(employee.getId())).isEmpty();
    }

    private Deliverer deliverer() {
        return new Deliverer(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
    }

}
