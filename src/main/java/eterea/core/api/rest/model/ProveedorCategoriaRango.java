/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
@Table(name = "provcategrango", uniqueConstraints = { @UniqueConstraint(columnNames = { "pcr_prc_id", "pcr_desde" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorCategoriaRango extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -8983772681166125490L;

	@Column(name = "pcr_prc_id")
	private Integer proveedorCategoriaId;
	
	@Column(name = "pcr_desde")
	private BigDecimal desde = BigDecimal.ZERO;
	
	@Column(name = "pcr_hasta")
	private BigDecimal hasta = BigDecimal.ZERO;
	
	@Column(name = "pcr_fijo")
	private BigDecimal fijo = BigDecimal.ZERO;
	
	@Column(name = "pcr_porcentaje")
	private BigDecimal porcentaje = BigDecimal.ZERO;
	
	@Id
	@Column(name = "pcr_id")
	private Long proveedorCategoriaRangoId;
}
