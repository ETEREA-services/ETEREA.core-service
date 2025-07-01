package eterea.core.service.service;

import eterea.core.service.kotlin.exception.RegistroCaeException;
import eterea.core.service.kotlin.model.RegistroCae;
import eterea.core.service.kotlin.repository.RegistroCaeRepository;
import eterea.core.service.model.Track;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegistroCaeService {

    private final RegistroCaeRepository repository;

    public RegistroCaeService(RegistroCaeRepository repository) {
        this.repository = repository;
    }

    public RegistroCae findByUnique(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
        return Objects.requireNonNull(repository.findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId, puntoVenta, numeroComprobante)).orElseThrow(() -> new RegistroCaeException(comprobanteId, puntoVenta, numeroComprobante));
    }

    public RegistroCae add(RegistroCae registroCae, Track track) {
        if (track != null) {
            registroCae.setTrackUuid(track.getUuid());
        }
        return repository.save(registroCae);
    }

}
