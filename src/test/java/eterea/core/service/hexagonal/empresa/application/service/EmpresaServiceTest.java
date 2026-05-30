package eterea.core.service.hexagonal.empresa.application.service;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;
import eterea.core.service.hexagonal.empresa.domain.ports.in.GetLastEmpresaUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {

    @Mock
    private GetLastEmpresaUseCase getLastEmpresaUseCase;

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    void findLast_ShouldDelegateToUseCase() {
        Empresa empresa = Empresa.builder().build();
        when(getLastEmpresaUseCase.findLast()).thenReturn(Optional.of(empresa));

        Optional<Empresa> result = empresaService.findLast();

        assertEquals(Optional.of(empresa), result);
        verify(getLastEmpresaUseCase).findLast();
    }

}
