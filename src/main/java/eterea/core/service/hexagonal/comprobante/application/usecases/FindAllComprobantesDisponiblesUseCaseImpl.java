package eterea.core.service.hexagonal.comprobante.application.usecases;

import eterea.core.service.hexagonal.comprobante.application.exception.ComprobanteException;
import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.in.FindAllComprobantesDisponiblesUseCase;
import eterea.core.service.hexagonal.comprobante.domain.ports.out.ComprobanteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindAllComprobantesDisponiblesUseCaseImpl implements FindAllComprobantesDisponiblesUseCase {

    private final ComprobanteRepository repository;

    @Override
    public List<Integer> findAllDisponibles() {
        Comprobante firstComprobante = repository.findFirstByOrderByComprobanteId()
                .orElseThrow(ComprobanteException::new);
        Comprobante lastComprobante = repository.findFirstByOrderByComprobanteIdDesc()
                .orElseThrow(ComprobanteException::new);

        Set<Integer> usados = repository.findAll().stream()
                .map(Comprobante::getComprobanteId)
                .collect(Collectors.toSet());

        return IntStream.rangeClosed(firstComprobante.getComprobanteId(), lastComprobante.getComprobanteId())
                .boxed()
                .filter(id -> !usados.contains(id))
                .collect(Collectors.toList());
    }
}
