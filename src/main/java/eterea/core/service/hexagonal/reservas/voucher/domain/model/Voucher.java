package eterea.core.service.hexagonal.reservas.voucher.domain.model;

import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.Hotel;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.Usuario;
import eterea.core.service.tool.Jsonifyable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voucher implements Jsonifyable {

    private Long voucherId;

    private OffsetDateTime fechaToma;

    private OffsetDateTime fechaServicio;

    private OffsetDateTime fechaVencimiento;

    @Builder.Default
    private LocalTime horaVencimiento = LocalTime.of(0, 0, 0);

    private String nombrePax;

    private Integer paxs;

    @Builder.Default
    private String subeEn = "";

    private String productos;

    private Byte tieneVoucher;

    private Long clienteId;

    private String observaciones;

    private Byte confirmado;

    private Byte pagaCacheuta;

    private Integer hotelId;

    @Builder.Default
    private String contacto = "";

    private Integer paxsReales;

    private Integer proveedorId;

    @Builder.Default
    private String planilla = "";

    private Long reservaId;

    private String numeroVoucher;

    private String usuario;

    private OffsetDateTime fechaRecepcion;

    private OffsetDateTime fechaEmision;

    private String numero;

    private Integer cantidadPax;

    private String nombre;

    private Byte conTraslado;

    private Integer paxsNoShow;

    private Integer reservaOrigenId;

    @Builder.Default
    private Byte fechaAbierta = 0;

    @Builder.Default
    private Byte ventaInternet = 0;

    private String trackUuid;

    private Usuario user;

    private Cliente cliente;

    private Hotel hotel;

    private Proveedor proveedor;

    private Reserva reserva;

}
