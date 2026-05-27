package eterea.core.service.hexagonal.comprobante.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.entity.ComprobanteEntity;
import org.springframework.stereotype.Component;

@Component
public class ComprobanteMapper {

    public Comprobante toDomain(ComprobanteEntity entity) {
        if (entity == null) {
            return null;
        }
        return Comprobante.builder()
                .comprobanteId(entity.getComprobanteId())
                .descripcion(entity.getDescripcion())
                .negocioId(entity.getNegocioId())
                .modulo(entity.getModulo())
                .stock(entity.getStock())
                .rendicion(entity.getRendicion())
                .ordenPago(entity.getOrdenPago())
                .aplicaPendiente(entity.getAplicaPendiente())
                .cuentaCorriente(entity.getCuentaCorriente())
                .debita(entity.getDebita())
                .iva(entity.getIva())
                .ganancias(entity.getGanancias())
                .aplicable(entity.getAplicable())
                .libroIva(entity.getLibroIva())
                .letraComprobante(entity.getLetraComprobante())
                .recibo(entity.getRecibo())
                .contado(entity.getContado())
                .transferencia(entity.getTransferencia())
                .imprime(entity.getImprime())
                .comprobanteIdAnulacion(entity.getComprobanteIdAnulacion())
                .centroStockIdEntrega(entity.getCentroStockIdEntrega())
                .centroStockIdRecibe(entity.getCentroStockIdRecibe())
                .diasVigencia(entity.getDiasVigencia())
                .concepto(entity.getConcepto())
                .personal(entity.getPersonal())
                .comanda(entity.getComanda())
                .puntoVenta(entity.getPuntoVenta())
                .codigoMozo(entity.getCodigoMozo())
                .transferir(entity.getTransferir())
                .numeroCuenta(entity.getNumeroCuenta())
                .nivel(entity.getNivel())
                .fiscalizadora(entity.getFiscalizadora())
                .invisible(entity.getInvisible())
                .comprobanteAfipId(entity.getComprobanteAfipId())
                .facturaElectronica(entity.getFacturaElectronica())
                .asociado(entity.getAsociado())
                .build();
    }

    public ComprobanteEntity toEntity(Comprobante domain) {
        if (domain == null) {
            return null;
        }
        ComprobanteEntity entity = new ComprobanteEntity();
        entity.setComprobanteId(domain.getComprobanteId());
        entity.setDescripcion(domain.getDescripcion());
        entity.setNegocioId(domain.getNegocioId());
        entity.setModulo(domain.getModulo());
        entity.setStock(domain.getStock());
        entity.setRendicion(domain.getRendicion());
        entity.setOrdenPago(domain.getOrdenPago());
        entity.setAplicaPendiente(domain.getAplicaPendiente());
        entity.setCuentaCorriente(domain.getCuentaCorriente());
        entity.setDebita(domain.getDebita());
        entity.setIva(domain.getIva());
        entity.setGanancias(domain.getGanancias());
        entity.setAplicable(domain.getAplicable());
        entity.setLibroIva(domain.getLibroIva());
        entity.setLetraComprobante(domain.getLetraComprobante());
        entity.setRecibo(domain.getRecibo());
        entity.setContado(domain.getContado());
        entity.setTransferencia(domain.getTransferencia());
        entity.setImprime(domain.getImprime());
        entity.setComprobanteIdAnulacion(domain.getComprobanteIdAnulacion());
        entity.setCentroStockIdEntrega(domain.getCentroStockIdEntrega());
        entity.setCentroStockIdRecibe(domain.getCentroStockIdRecibe());
        entity.setDiasVigencia(domain.getDiasVigencia());
        entity.setConcepto(domain.getConcepto());
        entity.setPersonal(domain.getPersonal());
        entity.setComanda(domain.getComanda());
        entity.setPuntoVenta(domain.getPuntoVenta());
        entity.setCodigoMozo(domain.getCodigoMozo());
        entity.setTransferir(domain.getTransferir());
        entity.setNumeroCuenta(domain.getNumeroCuenta());
        entity.setNivel(domain.getNivel());
        entity.setFiscalizadora(domain.getFiscalizadora());
        entity.setInvisible(domain.getInvisible());
        entity.setComprobanteAfipId(domain.getComprobanteAfipId());
        entity.setFacturaElectronica(domain.getFacturaElectronica());
        entity.setAsociado(domain.getAsociado());
        return entity;
    }
}
