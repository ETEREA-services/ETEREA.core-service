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
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "legajo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Legajo extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1920220723058781777L;

	@Id
	@Column(name = "leg_id")
	private Integer legajoId;

	@Column(name = "leg_apellido")
	private String apellido;

	@Column(name = "leg_nombre")
	private String nombre;

	@Column(name = "leg_nrodoc")
	private BigDecimal numeroDocumento;

	@Column(name = "leg_cuil")
	private String cuil;

}
