package eterea.core.service.hexagonal.reservas.voucher.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.Hotel;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.Usuario;
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
public class VoucherResponse {

    private Long voucherId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaToma;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaServicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaVencimiento;

    private LocalTime horaVencimiento;

    private String nombrePax;

    private Integer paxs;

    private String subeEn;

    private String productos;

    private Byte tieneVoucher;

    private Long clienteId;

    private String observaciones;

    private Byte confirmado;

    private Byte pagaCacheuta;

    private Integer hotelId;

    private String contacto;

    private Integer paxsReales;

    private Integer proveedorId;

    private String planilla;

    private Long reservaId;

    private String numeroVoucher;

    private String usuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaRecepcion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaEmision;

    private String numero;

    private Integer cantidadPax;

    private String nombre;

    private Byte conTraslado;

    private Integer paxsNoShow;

    private Integer reservaOrigenId;

    private Byte fechaAbierta;

    private Byte ventaInternet;

    private String trackUuid;

    private Usuario user;

    private Cliente cliente;

    private Hotel hotel;

    private Proveedor proveedor;

    private Reserva reserva;

}
