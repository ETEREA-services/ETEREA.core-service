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
@Table(name = "movconapertura", uniqueConstraints = { @UniqueConstraint(columnNames = { "mca_fecha", "mca_nrocomp", "mca_item" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimientoApertura extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4117745002453401924L;

	@Column(name = "mca_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "mca_nrocomp")
	private Integer orden;
	
	@Column(name = "mca_item")
	private Integer item;
	
	@Column(name = "mca_cuenta")
	private Long cuenta;
	
	@Column(name = "mca_concepto")
	private String concepto;
	
	@Column(name = "mca_neg_id")
	private Integer negocioId;
	
	@Column(name = "mca_debita")
	private Byte debita;
	
	@Column(name = "mca_importe")
	private BigDecimal importe = BigDecimal.ZERO;

	@Id
	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuentaMovimientoAperturaId;
}
