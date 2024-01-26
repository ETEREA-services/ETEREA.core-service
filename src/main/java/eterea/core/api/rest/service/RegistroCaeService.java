package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.exception.RegistroCaeException;
import eterea.core.api.rest.kotlin.model.RegistroCae;
import eterea.core.api.rest.kotlin.repository.RegistroCaeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroCaeService {

    private final RegistroCaeRepository repository;

    @Autowired
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
