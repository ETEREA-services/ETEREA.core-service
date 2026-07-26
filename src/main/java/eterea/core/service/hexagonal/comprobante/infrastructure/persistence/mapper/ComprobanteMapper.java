package eterea.core.service.hexagonal.comprobante.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.entity.ComprobanteEntity;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.persistence.mapper.CuentaMapper;
import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ComprobanteInvoiceDataResponse;
import eterea.core.service.hexagonal.invoicedata.infrastructure.mapper.ComprobanteAfipMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComprobanteMapper {

    private final CuentaMapper cuentaMapper;
    private final ComprobanteAfipMapper comprobanteAfipMapper;

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
                .cuenta(cuentaMapper.toDomain(entity.getCuenta()))
                .comprobanteAfip(entity.getComprobanteAfip())
                .build();
    }

    public ComprobanteEntity toEntity(Comprobante domain) {
        if (domain == null) {
            return null;
        }
        return ComprobanteEntity.builder()
                .comprobanteId(domain.getComprobanteId())
                .descripcion(domain.getDescripcion())
                .negocioId(domain.getNegocioId())
                .modulo(domain.getModulo())
                .stock(domain.getStock())
                .rendicion(domain.getRendicion())
                .ordenPago(domain.getOrdenPago())
                .aplicaPendiente(domain.getAplicaPendiente())
                .cuentaCorriente(domain.getCuentaCorriente())
                .debita(domain.getDebita())
                .iva(domain.getIva())
                .ganancias(domain.getGanancias())
                .aplicable(domain.getAplicable())
                .libroIva(domain.getLibroIva())
                .letraComprobante(domain.getLetraComprobante())
                .recibo(domain.getRecibo())
                .contado(domain.getContado())
                .transferencia(domain.getTransferencia())
                .imprime(domain.getImprime())
                .comprobanteIdAnulacion(domain.getComprobanteIdAnulacion())
                .centroStockIdEntrega(domain.getCentroStockIdEntrega())
                .centroStockIdRecibe(domain.getCentroStockIdRecibe())
                .diasVigencia(domain.getDiasVigencia())
                .concepto(domain.getConcepto())
                .personal(domain.getPersonal())
                .comanda(domain.getComanda())
                .puntoVenta(domain.getPuntoVenta())
                .codigoMozo(domain.getCodigoMozo())
                .transferir(domain.getTransferir())
                .numeroCuenta(domain.getNumeroCuenta())
                .nivel(domain.getNivel())
                .fiscalizadora(domain.getFiscalizadora())
                .invisible(domain.getInvisible())
                .comprobanteAfipId(domain.getComprobanteAfipId())
                .facturaElectronica(domain.getFacturaElectronica())
                .asociado(domain.getAsociado())
                .build();
    }

    public ComprobanteInvoiceDataResponse toInvoiceDataResponse(Comprobante comprobante) {
        if (comprobante == null) {
            return null;
        }
        return ComprobanteInvoiceDataResponse.builder()
                .letraComprobante(comprobante.getLetraComprobante())
                .contado(comprobante.getContado())
                .comprobanteAfip(comprobanteAfipMapper.toResponse(comprobante.getComprobanteAfip()))
                .build();
    }
}
