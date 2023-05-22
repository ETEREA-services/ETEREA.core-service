/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Habitacion extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8833001389821536502L;
	
	@Id
	@Column(name = "hab_numero")
	private Integer numero;
	
	@Column(name = "hab_hat_id")
	private Integer habitacionTipoId;
	
	@Column(name = "hab_cli_id")
	private Long clienteId;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long habitacionId;
	
}
