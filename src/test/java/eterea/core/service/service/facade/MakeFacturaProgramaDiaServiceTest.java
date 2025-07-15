package eterea.core.service.service.facade;

import eterea.core.service.client.report.MakeFacturaReportClient;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.Empresa;
import eterea.core.service.kotlin.model.Parametro;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.ReservaArticulo;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.model.Track;
import eterea.core.service.kotlin.model.RegistroCae;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.model.PosicionIva;
import eterea.core.service.model.ReservaContext;
import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.FacturacionElectronicaService;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.tool.ToolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {"app.enable-send-email=false"})
class MakeFacturaProgramaDiaServiceTest {

    @MockitoBean
    private ComprobanteService comprobanteService;
    @MockitoBean
    private ReservaService reservaService;
    @MockitoBean
    private EmpresaService empresaService;
    @MockitoBean
    private ReservaArticuloService reservaArticuloService;
    @MockitoBean
    private ParametroService parametroService;
    @MockitoBean
    private FacturacionElectronicaService facturacionElectronicaService;
    @MockitoBean
    private RegistroCaeService registroCaeService;
    @MockitoBean
    private ClienteService clienteService;
    @MockitoBean
    private ArticuloService articuloService;
    @MockitoBean
    private ReservaContextService reservaContextService;
    @MockitoBean
    private OrderNoteService orderNoteService;
    @MockitoBean
    private MakeFacturaReportClient makeFacturaReportClient;
    @MockitoBean
    private VoucherService voucherService;
    @MockitoBean
    private FacturacionService facturacionService;
    @MockitoBean
    private TrackService trackService;
    @MockitoBean
    private RegistraFacturaService registraFacturaService;

    @Autowired
    private MakeFacturaProgramaDiaService makeFacturaProgramaDiaService;

    private Track track;
    private final Long reservaId = 1L;
    private final Integer comprobanteId = 1;

    @BeforeEach
    void setUp() {
        track = new Track();
        track.setUuid(UUID.randomUUID().toString());
        when(trackService.startTracking(any())).thenReturn(track);
        when(trackService.endTracking(any())).thenReturn(track);
        when(trackService.failedTracking(any())).thenReturn(track);
    }

    @Test
    void testFacturaReservaGuardaTrackUuidEnEntidades() {
        // Arrange
        Comprobante comprobante = new Comprobante();
        comprobante.setComprobanteId(comprobanteId);
        comprobante.setFacturaElectronica((byte) 1);
        comprobante.setComprobanteAfipId(1);
        comprobante.setPuntoVenta(1);
        when(comprobanteService.findByComprobanteId(comprobanteId)).thenReturn(comprobante);

        Empresa empresa = new Empresa();
        when(empresaService.findTop()).thenReturn(empresa);

        Parametro parametro = new Parametro();
        parametro.setIva1(BigDecimal.valueOf(21));
        parametro.setIva2(BigDecimal.valueOf(10.5));
        when(parametroService.findTop()).thenReturn(parametro);

        Reserva reserva = new Reserva();
        reserva.setReservaId(reservaId);
        reserva.setFacturada((byte) 0);
        reserva.setVoucherId(1L);
        reserva.setClienteId(1L);
        when(reservaService.findByReservaId(reservaId)).thenReturn(reserva);

        Voucher voucher = new Voucher();
        voucher.setVoucherId(1L);
        voucher.setNumeroVoucher("123");
        when(voucherService.findByVoucherId(1L)).thenReturn(voucher);

        PosicionIva posicionIva = PosicionIva.builder().idPosicionIvaArca(5).build();
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        cliente.setCuit("20123456789");
        cliente.setTipoDocumento("DNI");
        cliente.setNumeroDocumento("12345678");
        cliente.setPosicion(posicionIva);
        when(clienteService.findByClienteId(1L)).thenReturn(cliente);

        Articulo articulo = new Articulo();
        articulo.setExento((byte) 0);
        articulo.setIva105((byte) 0);
        ReservaArticulo reservaArticulo = new ReservaArticulo();
        reservaArticulo.setArticuloId("1");
        reservaArticulo.setPrecioUnitario(BigDecimal.TEN);
        reservaArticulo.setCantidad(1);
        reservaArticulo.setArticulo(articulo);
        when(reservaArticuloService.findAllByReservaId(reservaId)).thenReturn(java.util.List.of(reservaArticulo));
        when(articuloService.findByArticuloId("1")).thenReturn(articulo);

        ReservaContext reservaContext = ReservaContext.builder()
                .reservaId(reservaId)
                .voucherId(1L)
                .orderNumberId(123L)
                .facturaPendiente((byte)1)
                .envioPendiente((byte)1)
                .facturaArca((byte)0)
                .facturaTries(0)
                .envioTries(0)
                .build();
        when(reservaContextService.findByVoucherId(any())).thenReturn(reservaContext);
        when(reservaContextService.update(any(), any())).thenReturn(reservaContext);
        when(reservaContextService.add(any())).thenReturn(reservaContext);

        FacturacionDto facturacionDto = FacturacionDto.builder()
                .tipoDocumento(80)
                .documento("20123456789")
                .tipoAfip(1)
                .puntoVenta(1)
                .total(BigDecimal.TEN)
                .exento(BigDecimal.ZERO)
                .neto(BigDecimal.TEN)
                .neto105(BigDecimal.ZERO)
                .iva(BigDecimal.ZERO)
                .iva105(BigDecimal.ZERO)
                .idCondicionIva(5)
                .resultado("A")
                .vencimientoCae("20250715")
                .fechaComprobante("20250715")
                .cae("123456")
                .numeroComprobante(1L)
                .build();
        when(facturacionElectronicaService.makeFactura(any())).thenReturn(facturacionDto);

        OrderNote orderNote = new OrderNote();
        when(orderNoteService.findByOrderNumberId(any())).thenReturn(orderNote);
        when(registraFacturaService.markReservaContextFacturada(any(), any(), any())).thenReturn(reservaContext);

        RegistroCae registroCae = new RegistroCae.Builder()
                .comprobanteId(comprobanteId)
                .puntoVenta(1)
                .numeroComprobante(1L)
                .clienteId(1L)
                .cuit("")
                .total(BigDecimal.TEN)
                .exento(BigDecimal.ZERO)
                .neto(BigDecimal.TEN)
                .neto105(BigDecimal.ZERO)
                .iva(BigDecimal.ZERO)
                .iva105(BigDecimal.ZERO)
                .cae("123456")
                .fecha("15072025")
                .caeVencimiento("15072025")
                .tipoDocumento(80)
                .numeroDocumento(new BigDecimal("20123456789"))
                .trackUuid(track.getUuid())
                .build();
        when(registroCaeService.add(any())).thenReturn(registroCae);

        ClienteMovimiento clienteMovimiento = new ClienteMovimiento();
        clienteMovimiento.setClienteMovimientoId(1L);
        when(facturacionService.registraTransaccionFacturaProgramaDia(any(), any(), any(), any(), any(), any(), any(), any(Track.class), anyBoolean()))
                .thenReturn(clienteMovimiento);

        // Act
        makeFacturaProgramaDiaService.facturaReserva(reservaId, comprobanteId, track);

        // Assert
        ArgumentCaptor<RegistroCae> registroCaeCaptor = ArgumentCaptor.forClass(RegistroCae.class);
        verify(registroCaeService).add(registroCaeCaptor.capture());
        assertThat(registroCaeCaptor.getValue().getTrackUuid()).isEqualTo(track.getUuid());

        ArgumentCaptor<Track> trackCaptorForFacturacion = ArgumentCaptor.forClass(Track.class);
        verify(facturacionService).registraTransaccionFacturaProgramaDia(any(), any(), any(), any(), any(), any(), any(), trackCaptorForFacturacion.capture(), anyBoolean());
        assertThat(trackCaptorForFacturacion.getValue().getUuid()).isEqualTo(track.getUuid());

        ArgumentCaptor<Track> trackCaptorForEnd = ArgumentCaptor.forClass(Track.class);
        verify(trackService, atLeastOnce()).endTracking(trackCaptorForEnd.capture());
        assertThat(trackCaptorForEnd.getValue().getUuid()).isEqualTo(track.getUuid());
    }
}
