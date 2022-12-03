/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "retgcias")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RetencionGanancia extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -465314950963615320L;

	@Column(name = "reg_val_clave")
	private Long valorMovimientoId;
	
	@Column(name = "reg_neto_ac")
	private BigDecimal netoAcumulado = BigDecimal.ZERO;
	
	@Column(name = "reg_neto_op")
	private BigDecimal netoOperacion = BigDecimal.ZERO;
	
	@Column(name = "reg_prc_id")
	private Integer proveedorCategoriaId;
	
	@Column(name = "reg_exento")
	private BigDecimal exento = BigDecimal.ZERO;
	
	@Column(name = "reg_fijo")
	private BigDecimal fijo = BigDecimal.ZERO;
	
	@Column(name = "reg_porcentaje")
	private BigDecimal porcentaje = BigDecimal.ZERO;
	
	@Id
	@Column(name = "reg_id")
	private Long retencionGananciaId;

}
