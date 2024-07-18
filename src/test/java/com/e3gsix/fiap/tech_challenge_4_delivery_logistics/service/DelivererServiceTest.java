package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository.DelivererRepository;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.impl.DelivererServiceImpl;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.UF;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.VehicleType;
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

public class DelivererServiceTest {

    private DelivererService delivererService;
    @Mock
    private DelivererRepository delivererRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        delivererService = new DelivererServiceImpl(delivererRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class CreateEmployee {
        @Test
        void create_newDeliverer_successful() {
            Long expectedId = 1L;

            DelivererCreationRequestDTO delivererDTO = new DelivererCreationRequestDTO(
                    "John Doe",
                    VehicleType.TRUCK,
                    UF.AC
            );

            Deliverer expectedDeliverer = delivererDTO.toModel();
            expectedDeliverer.setId(expectedId);

            when(delivererRepository.save(any(Deliverer.class))).thenReturn(expectedDeliverer);

            Long idCreatedDeliverer = delivererService.create(delivererDTO);

            verify(delivererRepository, times(1)).save(any(Deliverer.class));

            assertThat(idCreatedDeliverer)
                    .isNotNull()
                    .isEqualTo(expectedId);
        }
    }

    @Nested
    class findEmployee {


        @Test
        void findById_ExistingDeliverer_FindWithSuccess() {
            var deliverer = deliverer();
            when(delivererRepository.findById(anyLong())).thenReturn(Optional.of(deliverer));
            var employeeFound = delivererService.findById(deliverer.getId());
            verify(delivererRepository, times(1)).findById(deliverer.getId());
            assertThat(employeeFound).isEqualTo(deliverer);
        }

        @Test
        void find_employeeWithOtherId_successful() {
            var deliverer = deliverer();
            when(delivererRepository.findById(anyLong())).thenReturn(Optional.empty());
            assertThatThrownBy(() -> delivererService.findById(deliverer.getId()))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Entregador com ID: " + deliverer.getId() + " não encontrado.");
            verify(delivererRepository, times(1)).findById(deliverer.getId());
        }

    }


    @Nested
    class UpdateEmployee {

        @Test
        void update_Employee_successful() {
            var deliverer = deliverer();
            when(delivererRepository.findById(anyLong())).thenReturn(Optional.of(deliverer));
            when(delivererRepository.save(any(Deliverer.class))).thenReturn(deliverer);
            var employeeFound = delivererService.findById(deliverer.getId());
            employeeFound.setName("Silva");
            var employeeUpdated = delivererService.alterEmployee(deliverer.getId(), employeeFound);
            verify(delivererRepository, times(1)).save(any(Deliverer.class));
            assertThat(employeeUpdated).isInstanceOf(Deliverer.class)
                    .isNotNull()
                    .extracting(Deliverer::getName).isEqualTo("Silva");
        }

        @Test
        void update_employeeWrongId_successful() {
            var deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, UF.SP);
            var deliverer2 = new Deliverer(2L, "Randon", VehicleType.CAR, UF.SP);

            when(delivererRepository.findById(anyLong())).thenReturn(Optional.of(deliverer));
            when(delivererRepository.save(any(Deliverer.class))).thenReturn(deliverer);
            assertThatThrownBy(() -> delivererService.alterEmployee(deliverer.getId(),deliverer2))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Entregador não apresenta o ID correto");
            verify(delivererRepository, times(1)).findById(deliverer.getId());
        }
    }

    @Test
    void delete_Employee_successful() {
        var deliverer = deliverer();
        doNothing().when(delivererRepository).deleteById(deliverer.getId());
        delivererService.deleteEmployee(deliverer.getId());
        verify(delivererRepository, times(1)).deleteById(deliverer.getId());
    }

    private Deliverer deliverer() {
        return new Deliverer(1L, "Randon", VehicleType.CAR, UF.SP);
    }
}
