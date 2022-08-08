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
@Table(name = "movconajusted")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimientoAjusteDetalle extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 5351748346844864603L;

	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuentaMovimientoAjusteDetalleId;
	
	@Column(name = "mcd_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "mcd_nrocomp")
	private Integer ordenContable;
	
	@Column(name = "mcd_orden")
	private Integer orden;
	
	@Column(name = "mcd_neg_id")
	private Integer negocioId;
	
	@Column(name = "mcd_cuenta")
	private Long cuenta;
	
	@Column(name = "mcd_cmp_id")
	private Integer comprobanteId;
	
	@Column(name = "mcd_item")
	private Integer item;
	
	@Column(name = "mcd_concepto")
	private String concepto;
	
	@Column(name = "mcd_debita")
	private Byte debita;
	
	@Column(name = "mcd_importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "mcd_cgosub")
	private Long subrubroId;
	
	@Column(name = "mcd_cgoprov")
	private Long proveedorId;
	
	@Column(name = "mcd_cgoclie")
	private Long clienteId;
	
}
