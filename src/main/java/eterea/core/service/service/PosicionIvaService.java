package eterea.core.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.exception.PosicionIvaException;
import eterea.core.service.model.PosicionIva;
import eterea.core.service.repository.PosicionIvaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PosicionIvaService {

    private final PosicionIvaRepository repository;

    public PosicionIvaService(PosicionIvaRepository repository) {
        this.repository = repository;
    }

    public PosicionIva findByPosicionId(Integer posicionId) {
        log.debug("Processing PosicionIvaService.findByPosicionId({})", posicionId);
        return repository.findByPosicionId(posicionId).orElseThrow(() -> new PosicionIvaException(posicionId));
    }

}
