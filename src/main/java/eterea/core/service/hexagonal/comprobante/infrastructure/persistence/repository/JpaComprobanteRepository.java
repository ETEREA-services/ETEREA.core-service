package eterea.core.service.hexagonal.comprobante.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.entity.ComprobanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaComprobanteRepository extends JpaRepository<ComprobanteEntity, Integer> {

    List<ComprobanteEntity> findAllByFacturaElectronicaAndAsociado(Byte facturaElectronica, Byte asociado);

    List<ComprobanteEntity> findAllByModuloAndTransferirAndInvisible(Integer modulo, Byte transferir, Byte invisible);

    List<ComprobanteEntity> findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(
            Integer modulo, Byte transferir, Byte invisible, Byte debita, Integer comprobanteId);

    List<ComprobanteEntity> findAllByModuloAndTransferirAndInvisibleAndDebita(
            Integer modulo, Byte transferir, Byte invisible, Byte debita);

    List<ComprobanteEntity> findAllByModuloAndTransferirAndInvisibleAndComprobanteId(
            Integer modulo, Byte transferir, Byte invisible, Integer comprobanteId);

    List<ComprobanteEntity> findAllByModuloAndLibroIva(Integer modulo, Byte libroIva);

    List<ComprobanteEntity> findAllByModuloAndDebitaAndLibroIva(Integer modulo, Byte debita, Byte libroIva);

    Optional<ComprobanteEntity> findByComprobanteId(Integer comprobanteId);

    Optional<ComprobanteEntity> findFirstByOrderByComprobanteId();

    Optional<ComprobanteEntity> findFirstByOrderByComprobanteIdDesc();

}
