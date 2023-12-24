/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import eterea.core.api.rest.kotlin.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "movcontemp")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimientoTemporal extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 658338848427029884L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuentaMovimientoTemporalId;
	
	@Column(name = "mco_neg_id")
	private Integer negocioId;
	
	@Column(name = "cuenta")
	private Long cuenta;
	
	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "cgotcomp")
	private Integer comprobanteId;
	
	@Column(name = "nrocomp")
	private Integer orden;
	
	private Integer item;
	private String concepto;
	private Byte debita;
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "cgosub")
	private Long subrubroId;
	
	@Column(name = "cgoprov")
	private Long proveedorId;
	
	@Column(name = "cgoclie")
	private Long clienteId;
	
	@Column(name = "nroasien")
	private Long numeroAsiento;
	
	@Column(name = "mco_cic_id")
	private Long cierreCajaId;
	
	@Column(name = "mco_nivel")
	private Integer nivel;
}
