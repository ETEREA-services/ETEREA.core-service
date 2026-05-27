package eterea.core.service.hexagonal.comprobante.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eterea.core.service.hexagonal.cuenta.infrastructure.persistence.entity.CuentaEntity;
import eterea.core.service.kotlin.model.ComprobanteAfip;
import eterea.core.service.model.Auditable;
import eterea.core.service.tool.Jsonifier;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tiposcomprob")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComprobanteEntity extends Auditable {

    @Id
    @Column(name = "codigo")
    private Integer comprobanteId;

    @Builder.Default
    private String descripcion = "";

    @Column(name = "tco_neg_id")
    private Integer negocioId;

    private Integer modulo;

    @Builder.Default
    private Byte stock = 0;

    @Builder.Default
    private Byte rendicion = 0;

    @Column(name = "opago")
    @Builder.Default
    private Byte ordenPago = 0;

    @Column(name = "aplicapend")
    @Builder.Default
    private Byte aplicaPendiente = 0;

    @Column(name = "ctacte")
    @Builder.Default
    private Byte cuentaCorriente = 0;

    @Builder.Default
    private Byte debita = 0;

    @Builder.Default
    private Byte iva = 0;

    @Builder.Default
    private Byte ganancias = 0;

    @Builder.Default
    private Byte aplicable = 0;

    @Column(name = "libroiva")
    @Builder.Default
    private Byte libroIva = 0;

    @Column(name = "tipocomprob")
    private String letraComprobante;

    @Builder.Default
    private Byte recibo = 0;

    @Builder.Default
    private Byte contado = 0;

    @Builder.Default
    private Byte transferencia = 0;

    @Builder.Default
    private Byte imprime = 0;

    @Column(name = "cgoanulacion")
    private Integer comprobanteIdAnulacion;

    @Column(name = "cgocentroentrega")
    private Integer centroStockIdEntrega;

    @Column(name = "cgocentrorecibe")
    private Integer centroStockIdRecibe;

    @Column(name = "diasvigencia")
    private Integer diasVigencia;

    @Builder.Default
    private Byte concepto = 0;

    @Builder.Default
    private Byte personal = 0;

    @Builder.Default
    private Byte comanda = 0;

    @Column(name = "tco_puntovta")
    @Builder.Default
    private Integer puntoVenta = 0;

    @Column(name = "cgomozo")
    @Builder.Default
    private Byte codigoMozo = 0;

    @Column(name = "tco_transferir")
    @Builder.Default
    private Byte transferir = 0;

    @Column(name = "tco_cgocontable")
    private Long numeroCuenta;

    @Column(name = "tco_nivel")
    @Builder.Default
    private Integer nivel = 0;

    @Column(name = "tco_fiscalizadora")
    @Builder.Default
    private Byte fiscalizadora = 0;

    @Column(name = "tco_invisible")
    @Builder.Default
    private Byte invisible = 0;

    @Column(name = "tco_tipoafip")
    private Integer comprobanteAfipId;

    @Column(name = "tco_factelect")
    @Builder.Default
    private Byte facturaElectronica = 0;

    @Builder.Default
    private Byte asociado = 0;

    @OneToOne(optional = true)
    @JoinColumn(name = "tco_cgocontable", insertable = false, updatable = false)
    private CuentaEntity cuenta;

    @OneToOne(optional = true)
    @JoinColumn(name = "tco_tipoafip", insertable = false, updatable = false)
    private ComprobanteAfip comprobanteAfip;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
