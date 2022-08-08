/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "tiposcomprob")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Comprobante extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5004162435032433076L;

	@Id
	@Column(name = "codigo")
	private Integer comprobanteId;
	
    @Column(name = "descripcion")
    @NotNull
    private String descripcion = "";
    
	@Column(name = "tco_neg_id")
	private Integer negocioId;

	@Column(name = "modulo")
	private Integer modulo;

	@Column(name = "stock")
	@NotNull
	private Byte stock = 0;

	@Column(name = "rendicion")
	@NotNull
	private Byte rendicion = 0;

	@Column(name = "opago")
	@NotNull
	private Byte ordenPago = 0;

	@Column(name = "aplicapend")
	@NotNull
	private Byte aplicaPendiente = 0;

	@Column(name = "ctacte")
	@NotNull
	private Byte cuentaCorriente = 0;

	@Column(name = "debita")
	@NotNull
	private Byte debita = 0;

	@Column(name = "iva")
	@NotNull
	private Byte iva = 0;

	@Column(name = "ganancias")
	@NotNull
	private Byte ganancias = 0;

	@Column(name = "aplicable")
	@NotNull
	private Byte aplicable = 0;

	@Column(name = "libroiva")
	@NotNull
	private Byte libroIva = 0;

    @Column(name = "tipocomprob")
    private String letraComprobante;
    
	@Column(name = "recibo")
	@NotNull
	private Byte recibo = 0;

	@Column(name = "contado")
	@NotNull
	private Byte contado = 0;

	@Column(name = "transferencia")
	@NotNull
	private Byte transferencia = 0;

	@Column(name = "imprime")
	@NotNull
	private Byte imprime = 0;

	@Column(name = "cgoanulacion")
	private Integer comprobanteIdAnulacion;
	
	@Column(name = "cgocentroentrega")
	private Integer centroStockIdEntrega;

	@Column(name = "cgocentrorecibe")
	private Integer centroStockIdRecibe;

	@Column(name = "diasvigencia")
	private Integer diasVigencia;

	@Column(name = "concepto")
	@NotNull
	private Byte concepto = 0;

	@Column(name = "personal")
	@NotNull
	private Byte personal = 0;

	@Column(name = "comanda")
	@NotNull
	private Byte comanda = 0;

	@Column(name = "tco_puntovta")
	@NotNull
	private Integer puntoVenta = 0;

	@Column(name = "cgomozo")
	@NotNull
	private Byte codigoMozo = 0;

	@Column(name = "tco_transferir")
	@NotNull
	private Byte transferir = 0;

	@Column(name = "tco_cgocontable")
	private Long cuenta;
	
	@Column(name = "tco_nivel")
	@NotNull
	private Integer nivel = 0;

	@Column(name = "tco_fiscalizadora")
	@NotNull
	private Byte fiscalizadora = 0;

	@Column(name = "tco_invisible")
	@NotNull
	private Byte invisible = 0;

	@Column(name = "tco_tipoafip")
	private Integer comprobanteAfipId;

	@Column(name = "tco_factelect")
	@NotNull
	private Byte facturaElectronica = 0;
	
	private Byte asociado = 0;

}
