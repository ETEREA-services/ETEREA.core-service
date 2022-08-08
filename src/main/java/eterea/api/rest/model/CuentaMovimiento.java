/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "movcon", uniqueConstraints = { @UniqueConstraint(columnNames = { "fecha", "nrocomp", "item" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimiento extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7784892219631852163L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "nrocomp")
	private Integer orden;
	
	private Integer item;
	private Byte debita = 0;
	
	@Column(name = "mco_neg_id")
	private Integer negocioId;
	
	private Long cuenta;
	
	@Column(name = "cgotcomp")
	private Integer comprobanteId;
	
	private String concepto;
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "cgosub")
	private Long subrubroId;
	
	@Column(name = "cgoprov")
	private Long proveedorId;
	
	@Column(name = "cgoclie")
	private Long clienteId;
	
	@Column(name = "mco_cic_id")
	private Long cierreCajaId;
	
	@Column(name = "mco_nivel")
	private Integer nivel;
	
	@Column(name = "mco_mcf_firma")
	private Long firma;
	
	@Column(name = "mco_tas_id")
	private Integer tipoAsientoId;
	
	@Column(name = "articulomovimiento_id")
	private Long articuloMovimientoId;
	
	private Integer ejercicioId;
	private Byte inflacion;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuentaMovimientoId;

}
