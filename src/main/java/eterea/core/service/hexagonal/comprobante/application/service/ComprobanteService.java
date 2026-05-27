package eterea.core.service.hexagonal.comprobante.application.service;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.in.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ComprobanteService {

    private final FindAllComprobantesAsociablesUseCase findAllComprobantesAsociablesUseCase;
    private final FindAllComprobantesDisponiblesUseCase findAllComprobantesDisponiblesUseCase;
    private final FindAllComprobantesByModuloUseCase findAllComprobantesByModuloUseCase;
    private final FindComprobanteByIdUseCase findComprobanteByIdUseCase;

    public ComprobanteService(FindAllComprobantesAsociablesUseCase findAllComprobantesAsociablesUseCase,
                              FindAllComprobantesDisponiblesUseCase findAllComprobantesDisponiblesUseCase,
                              FindAllComprobantesByModuloUseCase findAllComprobantesByModuloUseCase,
                              FindComprobanteByIdUseCase findComprobanteByIdUseCase) {
        this.findAllComprobantesAsociablesUseCase = findAllComprobantesAsociablesUseCase;
        this.findAllComprobantesDisponiblesUseCase = findAllComprobantesDisponiblesUseCase;
        this.findAllComprobantesByModuloUseCase = findAllComprobantesByModuloUseCase;
        this.findComprobanteByIdUseCase = findComprobanteByIdUseCase;
    }

    public List<Comprobante> findAllAsociables() {
        return findAllComprobantesAsociablesUseCase.findAllAsociables();
    }

    public List<Comprobante> findAllAsociables(Byte debita) {
        return findAllComprobantesAsociablesUseCase.findAllAsociables(debita);
    }

    public List<Integer> findAllDisponibles() {
        return findAllComprobantesDisponiblesUseCase.findAllDisponibles();
    }

    public List<Comprobante> findAllByModulo(Integer modulo, Byte debita, Integer comprobanteId) {
        return findAllComprobantesByModuloUseCase.findAllByModulo(modulo, debita, comprobanteId);
    }

    public Comprobante findByComprobanteId(Integer comprobanteId) {
        return findComprobanteByIdUseCase.findByComprobanteId(comprobanteId);
    }

}
