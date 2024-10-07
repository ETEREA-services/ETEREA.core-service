package eterea.core.service.service;

import eterea.core.service.kotlin.exception.RegistroCaeException;
import eterea.core.service.kotlin.model.RegistroCae;
import eterea.core.service.kotlin.repository.RegistroCaeRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistroCaeService {

    private final RegistroCaeRepository repository;

    public RegistroCaeService(RegistroCaeRepository repository) {
        this.repository = repository;
    }

    public RegistroCae findByUnique(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
        return repository.findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId, puntoVenta, numeroComprobante).orElseThrow(() -> new RegistroCaeException(comprobanteId, puntoVenta, numeroComprobante));
    }

    public RegistroCae add(RegistroCae registroCae) {
        return repository.save(registroCae);
    }

}
