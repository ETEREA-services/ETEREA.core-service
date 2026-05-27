package eterea.core.service.hexagonal.comprobante.infrastructure.web.mapper;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.infrastructure.web.dto.ComprobanteResponse;
import eterea.core.service.hexagonal.cuenta.application.service.CuentaService;
import eterea.core.service.hexagonal.cuenta.infrastructure.web.mapper.CuentaDtoMapper;
import eterea.core.service.model.dto.ComprobanteDto;
import org.springframework.stereotype.Component;

@Component
public class ComprobanteDtoMapper {

    private final CuentaDtoMapper cuentaDtoMapper;
    private final CuentaService cuentaService;

    public ComprobanteDtoMapper(CuentaDtoMapper cuentaDtoMapper, CuentaService cuentaService) {
        this.cuentaDtoMapper = cuentaDtoMapper;
        this.cuentaService = cuentaService;
    }

    public ComprobanteResponse toResponse(Comprobante domain) {
        if (domain == null) {
            return null;
        }
        return ComprobanteResponse.builder()
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

    public ComprobanteDto toComprobanteDto(Comprobante domain) {
        if (domain == null) {
            return null;
        }
        return ComprobanteDto.builder()
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
                .cuenta(cuentaDtoMapper.toCuentaDto(cuentaService.findByNumeroCuenta(domain.getNumeroCuenta())))
                .build();
    }
}
