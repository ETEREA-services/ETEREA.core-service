/**
 *
 */
package eterea.core.service.service.facade;

import eterea.core.service.model.ArticuloMovimiento;
import eterea.core.service.kotlin.model.ClienteMovimientoPrevio;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.StockMovimiento;
import eterea.core.service.model.ClienteMovimiento;
import eterea.core.service.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import eterea.core.service.model.dto.ImpresionFiscalDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ImpresionFiscalService {

    private final ClienteService clienteService;
    private final ComprobanteService comprobanteService;
    private final ClienteMovimientoService clienteMovimientoService;
    private final ClienteMovimientoPrevioService clienteMovimientoPrevioService;
    private final ArticuloMovimientoTemporalService articuloMovimientoTemporalService;
    private final StockMovimientoService stockMovimientoService;
    private final ArticuloMovimientoService articuloMovimientoService;

    public ImpresionFiscalDto getData(String ipAddress, Long hWnd, Long clienteId, Integer comprobanteId,
                                      Long comprobanteOrigenId, Long stockMovimientoId) {
        Comprobante comprobante = null;
        Long nextNumeroFactura = null;
        if (comprobanteId > 0) {
            comprobante = comprobanteService.findByComprobanteId(comprobanteId);
            nextNumeroFactura = clienteMovimientoService.nextNumeroFactura(
                    comprobante.getLetraComprobante(),
                    comprobante.getPuntoVenta(),
                    0);
        }
        ClienteMovimiento clienteMovimientoAsociado = null;
        if (comprobanteOrigenId > 0) {
            clienteMovimientoAsociado = clienteMovimientoService.findByClienteMovimientoId(comprobanteOrigenId);
        }
        StockMovimiento stockMovimiento = null;
        List<ArticuloMovimiento> articuloMovimientos = null;
        if (stockMovimientoId > 0) {
            stockMovimiento = stockMovimientoService.findByStockMovimientoId(stockMovimientoId);
            articuloMovimientos = articuloMovimientoService.findAllByStockMovimientoId(stockMovimientoId);
        }
        ImpresionFiscalDto impresionFiscal = ImpresionFiscalDto.builder()
                .numeroFactura(nextNumeroFactura)
                .cliente(clienteService.findByClienteId(clienteId))
                .comprobante(comprobante)
                .articuloMovimientoTemporals(articuloMovimientoTemporalService.findAllByHWnd(ipAddress, hWnd, null))
                .comprobanteOrigen(clienteMovimientoAsociado)
                .stockMovimiento(stockMovimiento)
                .articuloMovimientos(articuloMovimientos)
                .build();
        log.debug("ImpresionFiscal -> {}", impresionFiscal.jsonify());
        return impresionFiscal;
    }

    public ImpresionFiscalDto getDataPrevio(Long clienteMovimientoPrevioId, Integer comprobanteId,
                                            Long comprobanteOrigenId) {
        Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
        log.debug("Comprobante -> {}", comprobante.jsonify());
        ClienteMovimiento clienteMovimiento = null;
        if (comprobanteOrigenId > 0) {
            clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(comprobanteOrigenId);
        }
        ClienteMovimientoPrevio clienteMovimientoPrevio = clienteMovimientoPrevioService
                .findByClienteMovimientoPrevioId(clienteMovimientoPrevioId);
        log.debug("ClienteMovimientoPrevio -> {}", clienteMovimientoPrevio.jsonify());
        ImpresionFiscalDto impresionFiscal = new ImpresionFiscalDto(
                clienteMovimientoService.nextNumeroFactura(
                        comprobante.getLetraComprobante(),
                        comprobante.getPuntoVenta(),
                        0
                ),
                clienteMovimientoPrevio.getCliente(),
                comprobante,
                null,
                clienteMovimiento,
                clienteMovimientoPrevio,
                null,
                null);
        log.debug("ImpresionFiscal -> {}", impresionFiscal.jsonify());
        return impresionFiscal;
    }

}
