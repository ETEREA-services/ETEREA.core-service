package eterea.core.service.service;

import eterea.core.service.kotlin.exception.FeriadoException;
import eterea.core.service.kotlin.model.Feriado;
import eterea.core.service.kotlin.repository.FeriadoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class FeriadoService {

    private final FeriadoRepository repository;

    public FeriadoService(FeriadoRepository repository) {
        this.repository = repository;
    }

    public Feriado findByFecha(OffsetDateTime fecha) {
        return repository.findByFecha(fecha).orElseThrow(() -> new FeriadoException(fecha));
    }

}
