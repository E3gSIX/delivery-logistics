package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
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

public class DelivererRepositoryTest {

    @Mock
    private DelivererRepository delivererRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void create_newEmployee_successful(){
        var deliverer = deliverer();
        when(delivererRepository.save(any(Deliverer.class))).thenReturn(deliverer);
        var employee = delivererRepository.save(deliverer);
        verify(delivererRepository, times(1)).save(deliverer);
        assertThat(employee).isInstanceOf(Deliverer.class)
                .isNotNull()
                .isEqualTo(deliverer);
    }

    @Test
    void find_newEmployee_successful(){
        var deliverer = deliverer();
        when(delivererRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(deliverer));
        var employeeOptional = delivererRepository.findById(deliverer.getId());
        verify(delivererRepository, times(1)).findById(deliverer.getId());
        assertThat(employeeOptional)
                .isPresent()
                .containsSame(deliverer);
    }

    @Test
    void update_Employee_sucessful(){
        var deliverer = deliverer();
        when(delivererRepository.save(any(Deliverer.class))).thenReturn(deliverer);
        var employee = delivererRepository.save(deliverer);
        employee.setName("Silva");
        var employeeUpdated = delivererRepository.save(employee);
        verify(delivererRepository, times(2)).save(any(Deliverer.class));
        assertThat(employeeUpdated).isInstanceOf(Deliverer.class)
                .isNotNull()
                .extracting(Deliverer::getName).isEqualTo("Silva");
    }

    @Test
    void delete_Employee_sucessful(){
        var deliverer = deliverer();
        doNothing().when(delivererRepository).deleteById(deliverer.getId());
        delivererRepository.deleteById(deliverer.getId());
        verify(delivererRepository, times(1)).deleteById(deliverer.getId());
    }

    private Deliverer deliverer() {
        return new Deliverer(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
    }

}
