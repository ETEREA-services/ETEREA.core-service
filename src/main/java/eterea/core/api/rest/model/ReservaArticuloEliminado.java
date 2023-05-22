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
@Table(name = "reservaarticuloe")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaArticuloEliminado extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4283647740861496408L;

	@Id
	@Column(name = "rae_id")
	private Long reservaArticuloEliminadoId;
	
	@Column(name = "rae_neg_id")
	private Integer negocioId;
	
	@Column(name = "rae_res_id")
	private Long reservaId;
	
	@Column(name = "rae_vou_id")
	private Long voucherId;
	
	@Column(name = "rae_art_id")
	private String articuloId;
	
	@Column(name = "rae_cantidad")
	private Integer cantidad;
	
	@Column(name = "rae_comision")
	private BigDecimal comision = BigDecimal.ZERO;
	
	@Column(name = "rae_preciounitariosincomision")
	private BigDecimal precioUnitarioSinComision = BigDecimal.ZERO;
	
	@Column(name = "rae_preciounitario")
	private BigDecimal precioUnitario = BigDecimal.ZERO;
	
	@Column(name = "rae_preciocomprado")
	private BigDecimal precioComprado = BigDecimal.ZERO;
	
	@Column(name = "rae_observaciones")
	private String observaciones;
	
	@Column(name = "rae_rar_id")
	private Long reservaArticuloId;

}
