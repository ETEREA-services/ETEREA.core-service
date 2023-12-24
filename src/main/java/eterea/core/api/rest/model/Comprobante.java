package eterea.core.api.rest.model;

import eterea.core.api.rest.kotlin.model.Auditable;
import eterea.core.api.rest.kotlin.model.Cuenta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tiposcomprob")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Comprobante extends Auditable implements Serializable {

	private static final long serialVersionUID = 5004162435032433076L;

	@Id
	@Column(name = "codigo")
	private Integer comprobanteId;

	private String descripcion = "";

	@Column(name = "tco_neg_id")
	private Integer negocioId;

	private Integer modulo;
	private Byte stock = 0;
	private Byte rendicion = 0;

	@Column(name = "opago")
	private Byte ordenPago = 0;

	@Column(name = "aplicapend")
	private Byte aplicaPendiente = 0;

	@Column(name = "ctacte")
	private Byte cuentaCorriente = 0;

	private Byte debita = 0;
	private Byte iva = 0;
	private Byte ganancias = 0;
	private Byte aplicable = 0;

	@Column(name = "libroiva")
	private Byte libroIva = 0;

	@Column(name = "tipocomprob")
	private String letraComprobante;

	private Byte recibo = 0;
	private Byte contado = 0;
	private Byte transferencia = 0;
	private Byte imprime = 0;

	@Column(name = "cgoanulacion")
	private Integer comprobanteIdAnulacion;

	@Column(name = "cgocentroentrega")
	private Integer centroStockIdEntrega;

	@Column(name = "cgocentrorecibe")
	private Integer centroStockIdRecibe;

	@Column(name = "diasvigencia")
	private Integer diasVigencia;

	private Byte concepto = 0;
	private Byte personal = 0;
	private Byte comanda = 0;

	@Column(name = "tco_puntovta")
	private Integer puntoVenta = 0;

	@Column(name = "cgomozo")
	private Byte codigoMozo = 0;

	@Column(name = "tco_transferir")
	private Byte transferir = 0;

	@Column(name = "tco_cgocontable")
	private Long numeroCuenta;

	@Column(name = "tco_nivel")
	private Integer nivel = 0;

	@Column(name = "tco_fiscalizadora")
	private Byte fiscalizadora = 0;

	@Column(name = "tco_invisible")
	private Byte invisible = 0;

	@Column(name = "tco_tipoafip")
	private Integer comprobanteAfipId;

	@Column(name = "tco_factelect")
	private Byte facturaElectronica = 0;

	private Byte asociado = 0;

	@OneToOne(optional = true)
	@JoinColumn(name = "tco_cgocontable", insertable = false, updatable = false)
	private Cuenta cuenta;

}
