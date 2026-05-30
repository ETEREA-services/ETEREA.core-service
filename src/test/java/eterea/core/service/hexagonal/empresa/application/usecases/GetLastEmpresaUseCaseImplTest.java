package eterea.core.service.hexagonal.empresa.application.usecases;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;
import eterea.core.service.hexagonal.empresa.domain.ports.out.EmpresaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetLastEmpresaUseCaseImplTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private GetLastEmpresaUseCaseImpl getLastEmpresaUseCase;

    @Test
    void findLast_WhenEmpresaExistsAndBusinessIdIsNotNull_ShouldReturnEmpresa() {
        UUID businessId = UUID.randomUUID();
        Empresa empresa = Empresa.builder().businessId(businessId).build();

        when(empresaRepository.findLast()).thenReturn(Optional.of(empresa));

        Optional<Empresa> result = getLastEmpresaUseCase.findLast();

        assertTrue(result.isPresent());
        assertEquals(businessId, result.get().getBusinessId());
        verify(empresaRepository, never()).save(any());
    }

    @Test
    void findLast_WhenEmpresaExistsAndBusinessIdIsNull_ShouldGenerateUuidAndSave() {
        Empresa empresa = Empresa.builder().businessId(null).build();

        when(empresaRepository.findLast()).thenReturn(Optional.of(empresa));

        Optional<Empresa> result = getLastEmpresaUseCase.findLast();

        assertTrue(result.isPresent());
        assertNotNull(result.get().getBusinessId());
        verify(empresaRepository).save(empresa);
    }

    @Test
    void findLast_WhenEmpresaDoesNotExist_ShouldReturnEmpty() {
        when(empresaRepository.findLast()).thenReturn(Optional.empty());

        Optional<Empresa> result = getLastEmpresaUseCase.findLast();

        assertTrue(result.isEmpty());
        verify(empresaRepository, never()).save(any());
    }
}
