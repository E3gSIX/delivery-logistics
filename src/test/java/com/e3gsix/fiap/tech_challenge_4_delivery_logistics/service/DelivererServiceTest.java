package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.exception.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.UF;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.VehicleType;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository.DelivererRepository;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.impl.DelivererServiceImpl;
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
            when(delivererRepository.findById(deliverer.getId())).thenReturn(Optional.of(deliverer));

            DelivererDTO delivererFound = delivererService.findById(deliverer.getId());

            verify(delivererRepository, times(1)).findById(deliverer.getId());

            assertThat(delivererFound).satisfies(found -> {
                assertThat(found.name()).isEqualTo(deliverer.getName());
                assertThat(found.vehicleType()).isEqualTo(deliverer.getVehicleType());
                assertThat(found.uf()).isEqualTo(deliverer.getUf());
            });
        }

        @Test
        void find_employeeWithOtherId_successful() {
            var deliverer = deliverer();
            when(delivererRepository.findById(anyLong())).thenReturn(Optional.empty());
            assertThatThrownBy(() -> delivererService.findById(deliverer.getId()))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Entregador com ID '" + deliverer.getId() + "' não encontrado.");
            verify(delivererRepository, times(1)).findById(deliverer.getId());
        }

    }


    @Nested
    class UpdateEmployee {

        @Test
        void update_ExistingDeliverer_UpdateWithSuccess() {
            Deliverer deliverer = deliverer();
            Deliverer delivererToUpdate = new Deliverer(deliverer.getId(), "John", VehicleType.TRUCK, UF.AL);

            when(delivererRepository.findById(deliverer.getId())).thenReturn(Optional.of(deliverer));
            when(delivererRepository.save(any(Deliverer.class))).thenReturn(delivererToUpdate);

            var deliveredUpdated = delivererService.update(deliverer.getId(), delivererToUpdate);

            verify(delivererRepository, times(1)).save(any(Deliverer.class));

            assertThat(deliveredUpdated).isInstanceOf(DelivererDTO.class)
                    .isNotNull().satisfies(updated -> {
                        assertThat(updated.name()).isEqualTo(delivererToUpdate.getName());
                        assertThat(updated.vehicleType()).isEqualTo(delivererToUpdate.getVehicleType());
                        assertThat(updated.uf()).isEqualTo(delivererToUpdate.getUf());
                    });
        }

        @Test
        void update_employeeWrongId_successful() {
            var deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, UF.SP);
            var deliverer2 = new Deliverer(2L, "Randon", VehicleType.CAR, UF.SP);

            when(delivererRepository.findById(anyLong())).thenReturn(Optional.of(deliverer));
            when(delivererRepository.save(any(Deliverer.class))).thenReturn(deliverer);
            assertThatThrownBy(() -> delivererService.update(deliverer.getId(), deliverer2))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Entregador não apresenta o ID correto");
            verify(delivererRepository, times(1)).findById(deliverer.getId());
        }
    }

    @Test
    void delete_Employee_successful() {
        var deliverer = deliverer();
        doNothing().when(delivererRepository).deleteById(deliverer.getId());
        delivererService.delete(deliverer.getId());
        verify(delivererRepository, times(1)).deleteById(deliverer.getId());
    }

    private Deliverer deliverer() {
        return new Deliverer(1L, "Randon", VehicleType.CAR, UF.SP);
    }
}
