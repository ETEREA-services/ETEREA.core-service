package eterea.core.service.hexagonal.comprobante.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.out.ComprobanteRepository;
import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.mapper.ComprobanteMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaComprobanteRepositoryAdapter implements ComprobanteRepository {

    private final JpaComprobanteRepository jpaRepository;
    private final ComprobanteMapper mapper;

    public JpaComprobanteRepositoryAdapter(JpaComprobanteRepository jpaRepository, ComprobanteMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Comprobante> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comprobante> findAllByFacturaElectronicaAndAsociado(Byte facturaElectronica, Byte asociado) {
        return jpaRepository.findAllByFacturaElectronicaAndAsociado(facturaElectronica, asociado).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comprobante> findAllByModuloAndTransferirAndInvisible(Integer modulo, Byte transferir, Byte invisible) {
        return jpaRepository.findAllByModuloAndTransferirAndInvisible(modulo, transferir, invisible).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(Integer modulo, Byte transferir, Byte invisible, Byte debita, Integer comprobanteId) {
        return jpaRepository.findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(modulo, transferir, invisible, debita, comprobanteId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndDebita(Integer modulo, Byte transferir, Byte invisible, Byte debita) {
        return jpaRepository.findAllByModuloAndTransferirAndInvisibleAndDebita(modulo, transferir, invisible, debita).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndComprobanteId(Integer modulo, Byte transferir, Byte invisible, Integer comprobanteId) {
        return jpaRepository.findAllByModuloAndTransferirAndInvisibleAndComprobanteId(modulo, transferir, invisible, comprobanteId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comprobante> findAllByModuloAndLibroIva(Integer modulo, Byte libroIva) {
        return jpaRepository.findAllByModuloAndLibroIva(modulo, libroIva).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comprobante> findAllByModuloAndDebitaAndLibroIva(Integer modulo, Byte debita, Byte libroIva) {
        return jpaRepository.findAllByModuloAndDebitaAndLibroIva(modulo, debita, libroIva).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Comprobante> findByComprobanteId(Integer comprobanteId) {
        return jpaRepository.findByComprobanteId(comprobanteId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Comprobante> findFirstByOrderByComprobanteId() {
        return jpaRepository.findFirstByOrderByComprobanteId()
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Comprobante> findFirstByOrderByComprobanteIdDesc() {
        return jpaRepository.findFirstByOrderByComprobanteIdDesc()
                .map(mapper::toDomain);
    }
}
