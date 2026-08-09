package eterea.core.service.hexagonal.reservas.voucher.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.entity.ProveedorEntity;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.Hotel;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.Usuario;
import eterea.core.service.model.Auditable;
import eterea.core.service.tool.Jsonifyable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEntity extends Auditable implements Jsonifyable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vou_id")
    private Long voucherId;

    @Column(name = "vou_fechareserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaToma;

    @Column(name = "vou_fechain")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaServicio;

    @Column(name = "vou_vto")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaVencimiento;

    @Builder.Default
    @Column(name = "vou_horavto")
    private LocalTime horaVencimiento = LocalTime.of(0, 0, 0);

    @Column(name = "vou_nombrepax")
    private String nombrePax;

    @Column(name = "vou_paxs")
    private Integer paxs;

    @Builder.Default
    @Column(name = "vou_subeen")
    private String subeEn = "";

    @Column(name = "vou_productos")
    private String productos;

    @Column(name = "vou_voucher")
    private Byte tieneVoucher;

    @Column(name = "vou_cli_id")
    private Long clienteId;

    @Column(name = "vou_observ")
    private String observaciones;

    @Column(name = "vou_confirmado")
    private Byte confirmado;

    private Byte pagaCacheuta;

    @Column(name = "vou_hot_id")
    private Integer hotelId;

    @Builder.Default
    @Column(name = "vou_cliente")
    private String contacto = "";

    @Column(name = "vou_paxsreales")
    private Integer paxsReales;

    @Column(name = "vou_pro_id")
    private Integer proveedorId;

    @Builder.Default
    @Column(name = "vou_planilla")
    private String planilla = "";

    @Column(name = "vou_res_id")
    private Long reservaId;

    @Column(name = "vou_nrovoucher")
    private String numeroVoucher;

    @Column(name = "vou_usuario")
    private String usuario;

    @Column(name = "vou_fecharecepcion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaRecepcion;

    @Column(name = "vou_fechaemision")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaEmision;

    @Column(name = "vou_numero")
    private String numero;

    @Column(name = "vou_cantidadpax")
    private Integer cantidadPax;

    @Column(name = "vou_nombre")
    private String nombre;

    @Column(name = "vou_contraslado")
    private Byte conTraslado;

    @Column(name = "vou_paxsnoshow")
    private Integer paxsNoShow;

    @Column(name = "vou_reo_id")
    private Integer reservaOrigenId;

    @Builder.Default
    private Byte fechaAbierta = 0;

    @Builder.Default
    private Byte ventaInternet = 0;

    private String trackUuid;

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_usuario", insertable = false, updatable = false)
    private Usuario user;

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_cli_id", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_hot_id", insertable = false, updatable = false)
    private Hotel hotel;

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_pro_id", insertable = false, updatable = false)
    private ProveedorEntity proveedor;

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_res_id", insertable = false, updatable = false)
    private Reserva reserva;

}
