package eterea.core.service.hexagonal.transferencia.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.hexagonal.transferencia.domain.model.Transferencia;
import eterea.core.service.hexagonal.transferencia.domain.ports.in.FindTransferenciaByUniqueUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferenciaService {

    private final FindTransferenciaByUniqueUseCase findTransferenciaByUniqueUseCase;

    public TransferenciaService(FindTransferenciaByUniqueUseCase findTransferenciaByUniqueUseCase) {
        this.findTransferenciaByUniqueUseCase = findTransferenciaByUniqueUseCase;
    }

    public Transferencia findByUnique(Integer negocioIdDesde, Integer negocioIdHasta, Long numeroTransferencia) {
        Transferencia transferencia = findTransferenciaByUniqueUseCase.findByUnique(negocioIdDesde, negocioIdHasta, numeroTransferencia);
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
