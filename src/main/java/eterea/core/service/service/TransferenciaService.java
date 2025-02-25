package eterea.core.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.exception.TransferenciaException;
import eterea.core.service.kotlin.model.Transferencia;
import eterea.core.service.kotlin.repository.TransferenciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class TransferenciaService {

    private final TransferenciaRepository repository;

    public TransferenciaService(TransferenciaRepository repository) {
        this.repository = repository;
    }

    public Transferencia findByUnique(Integer negocioIdDesde, Integer negocioIdHasta, Long numeroTransferencia) {
        log.debug("Processing TransferenciaService.findByUnique() with parameters: negocioIdDesde: {}, negocioIdHasta: {}, numeroTransferencia: {}", negocioIdDesde, negocioIdHasta, numeroTransferencia);
        var transferencia = Objects.requireNonNull(repository.findByNegocioIdDesdeAndNegocioIdHastaAndNumeroTransferencia(negocioIdDesde,
                negocioIdHasta,
                numeroTransferencia)).orElseThrow(() -> new TransferenciaException(negocioIdDesde, negocioIdHasta, numeroTransferencia));
        logTransferencia(transferencia);
        return transferencia;
    }

    private void logTransferencia(Transferencia transferencia) {
        try {
            log.debug("Transferencia  {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(transferencia));
        } catch (JsonProcessingException e) {
            log.debug("Transferencia jsonify error -> {}", e.getMessage());
        }
    }

}
