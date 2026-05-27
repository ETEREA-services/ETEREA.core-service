package eterea.core.service.hexagonal.comprobante.domain.ports.out;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import java.util.List;
import java.util.Optional;

public interface ComprobanteRepository {

    List<Comprobante> findAll();

    List<Comprobante> findAllByFacturaElectronicaAndAsociado(Byte facturaElectronica, Byte asociado);

    List<Comprobante> findAllByModuloAndTransferirAndInvisible(Integer modulo, Byte transferir, Byte invisible);

    List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(
        Integer modulo, Byte transferir, Byte invisible, Byte debita, Integer comprobanteId);

    List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndDebita(
        Integer modulo, Byte transferir, Byte invisible, Byte debita);

    List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndComprobanteId(
        Integer modulo, Byte transferir, Byte invisible, Integer comprobanteId);

    List<Comprobante> findAllByModuloAndLibroIva(Integer modulo, Byte libroIva);

    List<Comprobante> findAllByModuloAndDebitaAndLibroIva(Integer modulo, Byte debita, Byte libroIva);

    Optional<Comprobante> findByComprobanteId(Integer comprobanteId);

    Optional<Comprobante> findFirstByOrderByComprobanteId();

    Optional<Comprobante> findFirstByOrderByComprobanteIdDesc();

}
