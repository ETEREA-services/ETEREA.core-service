package eterea.core.service.service;

import eterea.core.service.kotlin.exception.RegistroCaeException;
import eterea.core.service.kotlin.repository.RegistroCaeRepository;
import eterea.core.service.model.RegistroCae;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RegistroCaeService {

    private final RegistroCaeRepository repository;

    public RegistroCae findByUnique(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
        return Objects.requireNonNull(repository.findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId, puntoVenta, numeroComprobante)).orElseThrow(() -> new RegistroCaeException(comprobanteId, puntoVenta, numeroComprobante));
    }

    public RegistroCae add(RegistroCae registroCae) {
        return repository.save(registroCae);
    }

}
