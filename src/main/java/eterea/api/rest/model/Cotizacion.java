/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

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
@Table(name = "cotizacion")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Cotizacion extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3866125724002481992L;

	@Id
	@Column(name = "cot_id")
	private Integer cotizacionId;
	
	@Column(name = "cot_descripcion")
	private String descripcion;
	
}
