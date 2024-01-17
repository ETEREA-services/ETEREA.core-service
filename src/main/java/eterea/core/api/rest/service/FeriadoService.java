package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.exception.FeriadoException;
import eterea.core.api.rest.kotlin.model.Feriado;
import eterea.core.api.rest.kotlin.repository.FeriadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class FeriadoService {

    private final FeriadoRepository repository;

    @Autowired
    public FeriadoService(FeriadoRepository repository) {
        this.repository = repository;
    }

    public Feriado findByFecha(OffsetDateTime fecha) {
        return repository.findByFecha(fecha).orElseThrow(() -> new FeriadoException(fecha));
    }

}
