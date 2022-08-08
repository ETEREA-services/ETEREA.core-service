/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "movstock")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StockMovimiento extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 793598538514263780L;

	@Column(name = "cgocomprob")
	private Integer comprobanteId;
	
	@Column(name = "nrocompinterno")
	private Long numeroComprobanteInterno;
	
	@Column(name = "mst_neg_id")
	private Integer negocioId;
	
	@Id
	@Column(name = "clave")
	private Long stockMovimientoId;
	
	@Column(name = "mst_neg_id_desde")
	private Integer negocioIdDesde;
	
	@Column(name = "mst_cgocentrodesde")
	private Long centroStockDesde;
	
	@Column(name = "mst_neg_id_hasta")
	private Integer negocioIdHasta;
	
	@Column(name = "mst_cgocentrohasta")
	private Long centroStockIdHasta;
	
	@Column(name = "mst_cstidhnombre")
	private String centroStockHastaNombre;
	
	@Column(name = "fechareg")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaRegistro;
	
	@Column(name = "cgoprov")
	private Long proveedorId;
	
	@Column(name = "cgocli")
	private Long clienteId;
	
	@Column(name = "legajo")
	private Long legajo;
	
	@Column(name = "fechacomprob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaComprobante;
	
	@Column(name = "cgocomprob2")
	private Integer comprobanteIdFactura;
	
	@Column(name = "prefijo")
	private Integer prefijo;
	
	@Column(name = "nrocomprob")
	private Long numeroComprobante;
	
	@Column(name = "importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "letracomanda")
	private String letraComanda;
	
	@Column(name = "mst_observ")
	private String observaciones;
	
	@Column(name = "mst_cic_id")
	private Long cierreCajaId;
	
	@Column(name = "msr_cir_id")
	private Long cierreRestaurantId;
	
	@Column(name = "mst_nivel")
	private Integer nivel; 
	
	@Column(name = "mst_fechacontable")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaContable;
	
	@Column(name = "mst_nrocompcontable")
	private Integer ordenContable;
	
	@Column(name = "mst_neg_id_otro")
	private Integer negocioIdOtro;
	
	@Column(name = "mst_genauto")
	private Byte generacionAutomatica;
	
	@Column(name = "mst_pendiente")
	private Byte pendiente;
	
	@Column(name = "mst_rechazada")
	private Byte rechazada;
	
	@Column(name = "factura_proveedor")
	private Byte facturaProveedor;
	
	@Column(name = "neto_factura")
	private BigDecimal netoFactura = BigDecimal.ZERO;
	
	@Column(name = "neto_registrado")
	private BigDecimal netoRegistrado = BigDecimal.ZERO;
	
}
