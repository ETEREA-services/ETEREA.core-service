/**
 *
 */
package eterea.core.api.rest.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.api.rest.kotlin.model.*;
import eterea.core.api.rest.kotlin.repository.ReservaRepository;
import eterea.core.api.rest.service.*;
import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Comprobante;

/**
 * @author daniel
 */
@Service
@Slf4j
public class ReservaService {

    private final ReservaRepository repository;

    private final ClienteMovimientoService clienteMovimientoService;

    private final VoucherService voucherService;

    private final ComprobanteService comprobanteService;

    private final ReservaArticuloService reservaArticuloService;

    private final ArticuloService articuloService;

    private final ArticuloMovimientoService articuloMovimientoService;

    private final ConceptoFacturadoService conceptoFacturadoService;

    private final EmpresaService empresaService;

    private final VoucherProductoService voucherProductoService;

    @Autowired
    public ReservaService(ReservaRepository repository, ClienteMovimientoService clienteMovimientoService, VoucherService voucherService, ComprobanteService comprobanteService, ReservaArticuloService reservaArticuloService, ArticuloService articuloService, ArticuloMovimientoService articuloMovimientoService, ConceptoFacturadoService conceptoFacturadoService, EmpresaService empresaService, VoucherProductoService voucherProductoService) {
        this.repository = repository;
        this.clienteMovimientoService = clienteMovimientoService;
        this.voucherService = voucherService;
        this.comprobanteService = comprobanteService;
        this.reservaArticuloService = reservaArticuloService;
        this.articuloService = articuloService;
        this.articuloMovimientoService = articuloMovimientoService;
        this.conceptoFacturadoService = conceptoFacturadoService;
        this.empresaService = empresaService;
        this.voucherProductoService = voucherProductoService;
    }

    public List<Reserva> findTopPendientes() {
        return repository
                .findTop100ByVerificadaAndFacturadaAndEliminadaAndPagaCacheutaAndFacturadoFueraAndAnuladaAndClienteIdGreaterThan(
                        (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 0L,
                        Sort.by("fechaToma").descending().and(Sort.by("clienteId")));
    }

    @Transactional
    public void completeArticulos(Long clienteMovimientoId) {
        ClienteMovimiento clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(clienteMovimientoId);

        if (clienteMovimiento.getReservaId() == 0)
            return;
        Voucher voucher = voucherService.findByReservaId(clienteMovimiento.getReservaId());
        Reserva reserva = repository.findByReservaId(clienteMovimiento.getReservaId()).get();
        Comprobante comprobante = comprobanteService.findByComprobanteId(clienteMovimiento.getComprobanteId());
        int item = 0;
        List<ArticuloMovimiento> articuloMovs = new ArrayList<>();

        String numeroVoucher = "";
        if (!voucher.getNumeroVoucher().trim().isEmpty())
            numeroVoucher = " - No.Voucher: " + voucher.getNumeroVoucher();

        for (ReservaArticulo reservaArticulo : reservaArticuloService
                .findAllByReservaId(clienteMovimiento.getReservaId())) {
            if (!reservaArticulo.getObservaciones().trim().isEmpty())
                reservaArticulo.setObservaciones(reservaArticulo.getObservaciones() + numeroVoucher);
            addArticulo(clienteMovimiento, reservaArticulo, comprobante, ++item, reserva.getFacturarExtranjero(),
                    articuloMovs);
        }
    }

    @Transactional
    protected void addArticulo(ClienteMovimiento clienteMovimiento, ReservaArticulo reservaArticulo,
                               Comprobante comprobante, Integer item, Byte facturaExtranjero, List<ArticuloMovimiento> articuloMovs) {
        Articulo articulo = articuloService.findByArticuloId(reservaArticulo.getArticuloId());
        BigDecimal factorIva = new BigDecimal("1.21");
        if (articulo.getIva105() == 1)
            factorIva = new BigDecimal("1.105");
        if (articulo.getExento() == 1)
            factorIva = new BigDecimal("1.0");
        Integer factor = comprobante.getDebita() == 1 ? -1 : 1;

        if (articulo.getCentroStockId() != 1 || facturaExtranjero == 0) {
            ArticuloMovimiento articuloMovimiento = new ArticuloMovimiento();
            articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
            articuloMovimiento.setComprobanteId(clienteMovimiento.getComprobanteId());
            articuloMovimiento.setNegocioId(clienteMovimiento.getNegocioId());
            articuloMovimiento.setItem(item);
            articuloMovimiento.setFechaMovimiento(clienteMovimiento.getFechaComprobante());
            articuloMovimiento.setFechaFactura(clienteMovimiento.getFechaComprobante());
            articuloMovimiento.setCierreCajaId(clienteMovimiento.getCierreCajaId());
            articuloMovimiento.setArticuloId(reservaArticulo.getArticuloId());
            articuloMovimiento.setCentroStockId(articulo.getCentroStockId());
            BigDecimal preciounitario = reservaArticulo.getPrecioUnitario();
            articuloMovimiento.setPrecioUnitarioSinIva(preciounitario.divide(factorIva, 2, RoundingMode.HALF_UP));
            preciounitario = articuloMovimiento.getPrecioUnitarioSinIva().multiply(factorIva);
            preciounitario = preciounitario.setScale(2, RoundingMode.HALF_UP);
            articuloMovimiento.setPrecioUnitarioConIva(preciounitario);
            articuloMovimiento.setPrecioUnitario(articuloMovimiento.getPrecioUnitarioConIva());
            articuloMovimiento.setCantidad(new BigDecimal(factor * reservaArticulo.getCantidad()));
            articuloMovimiento.setTotal(
                    articuloMovimiento.getPrecioUnitario().multiply(new BigDecimal(reservaArticulo.getCantidad())));
            articuloMovimiento.setCuenta(articulo.getCuentaVentas());
            articuloMovimiento.setIva105(articulo.getIva105());
            articuloMovimiento.setExento(articulo.getExento());

            articuloMovimiento = articuloMovimientoService.add(articuloMovimiento);

            articuloMovs.add(articuloMovimiento);

            if (!reservaArticulo.getObservaciones().trim().isEmpty()) {
                ConceptoFacturado conceptoFacturado = new ConceptoFacturado();
                conceptoFacturado.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
                conceptoFacturado.setNumeroLinea(1);
                conceptoFacturado.setConcepto(reservaArticulo.getObservaciones());
                conceptoFacturado.setArticuloMovimientoId(articuloMovimiento.getArticuloMovimientoId());

                conceptoFacturado = conceptoFacturadoService.add(conceptoFacturado);
            }
        }
    }

    public Reserva copyFromVoucher(Voucher voucher) {
        return new Reserva.Builder()
                .voucherId(voucher.getVoucherId())
                .clienteId(voucher.getClienteId())
                .fechaToma(voucher.getFechaToma())
                .fechaInServicio(voucher.getFechaServicio())
                .fechaOutServicio(voucher.getFechaServicio())
                .fechaVencimiento(voucher.getFechaVencimiento())
                .nombrePax(voucher.getNombrePax())
                .cantidadPaxs(voucher.getPaxs())
                .observaciones(voucher.getObservaciones())
                .reservaOrigenId(voucher.getReservaOrigenId())
                .pagaCacheuta(voucher.getPagaCacheuta())
                .build();
    }

    public Reserva add(Reserva reserva) {
        return repository.save(reserva);
    }

    @Transactional
    public void generarReservaArticulo(Reserva reserva, List<VoucherProducto> voucherProductos) {
        Empresa empresa = empresaService.findTop();
        // Proceso que depura los artículos a eliminar y agregar
        // Para eliminar parto de todos los que ya están guardados y sacaré los que hay que guardar que ya estaban, los que queden serán eliminados
        Map<String, ReservaArticulo> collectionEliminar = reservaArticuloService.findAllByVoucherId(reserva.getReservaId(), reserva.getVoucherId()).stream().collect(Collectors.toMap(ReservaArticulo::getArticuloId, reservaArticulo -> reservaArticulo));
        // Para agregar parto de todos los artículos que corresponden al voucher y elimino los que ya están guardados, los que queden seran agregados
        Map<String, Articulo> collectionAgregar = articuloService.findAllByVoucher(voucherProductos).stream().collect(Collectors.toMap(Articulo::toString, Function.identity(), ((articulo, otherArticulo) -> articulo)));

        // Busco las claves a eliminar en ambas colecciones
        var claves = new ArrayList<String>();
        for (ReservaArticulo reservaArticulo : collectionEliminar.values()) {
            if (collectionAgregar.containsKey(reservaArticulo.getArticuloId())) {
                claves.add(reservaArticulo.getArticuloId());
            }
        }

        // Elimino las claves de ambas colecciones
        for (String clave : claves) {
            collectionEliminar.remove(clave);
            collectionAgregar.remove(clave);
        }

        // Elimino de la DB los registros de reservaarticulo sobrantes
        for (ReservaArticulo reservaArticulo : collectionEliminar.values()) {
            reservaArticuloService.deleteByReservaArticuloId(reservaArticulo.getReservaArticuloId());
        }

        // Agrego los artículos nuevos en reservaarticulo
        List<ReservaArticulo> reservaArticulos = new ArrayList<>();
        for (Articulo articulo : collectionAgregar.values()) {
            reservaArticulos.add(new ReservaArticulo.Builder()
                    .negocioId(empresa.getNegocioId())
                    .reservaId(reserva.getReservaId())
                    .voucherId(reserva.getVoucherId())
                    .articuloId(articulo.getArticuloId())
                    .precioUnitarioSinComision(articulo.getPrecioVentaConIva())
                    .build());

        }
        reservaArticulos = reservaArticuloService.saveAll(reservaArticulos);

        try {
            log.debug("reservaArticulos={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(reservaArticulos));
        } catch (JsonProcessingException e) {
            log.debug("something went wrong");
        }

        recalcularVoucher(reserva.getReservaId(), reserva.getVoucherId());
    }

    @Transactional
    public void recalcularVoucher(Long reservaId, Long voucherId) {
        Voucher voucher = voucherService.findByVoucherId(voucherId);

        int contador = 0;
        for (ReservaArticulo reservaArticulo : reservaArticuloService.findAllByVoucherId(reservaId, voucherId)) {
            contador++;
            VoucherProducto voucherProducto = voucherProductoService.findByArticuloId(voucherId, reservaArticulo.getArticuloId());
            reservaArticulo.setCantidad(voucherProducto.getCantidadPaxs());

            if (reservaArticulo.getPrecioUnitarioSinComision().compareTo(BigDecimal.ZERO) == 0) {
                reservaArticulo.setPrecioUnitarioSinComision(reservaArticulo.getArticulo().getPrecioVentaConIva());
            }

            reservaArticulo.setComision(comisionArticulo(reservaId, reservaArticulo.getArticuloId(), voucher.getClienteId()));
            BigDecimal complemento = BigDecimal.ONE.subtract(reservaArticulo.getComision()).setScale(2, RoundingMode.HALF_UP);
            reservaArticulo.setPrecioUnitario(reservaArticulo.getPrecioUnitarioSinComision().multiply(complemento).setScale(2, RoundingMode.HALF_UP));
            reservaArticulo.setObservaciones("");

            if (contador == 1 && voucher.getReservaId() > 0) {
                reservaArticulo.setObservaciones("RVA " + voucher.getReservaId() + " " + voucher.getNombrePax() + " x " + voucher.getPaxs() + " " + voucher.getProductos() + " " + voucher.getFechaServicio());
            }

            reservaArticulo = reservaArticuloService.update(reservaArticulo, reservaArticulo.getReservaArticuloId());

        }
    }

    private BigDecimal comisionArticulo(Long reservaId, String articuloId, Long clienteId) {
        // TODO: simplificado para avanzar
        return BigDecimal.ZERO;
    }

}
