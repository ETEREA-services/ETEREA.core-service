/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "reservaservicio")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaServicio extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8631186379592940639L;

	@Column(name = "reservaid")
	private Long reservaId;
	
	@Column(name = "prestadorid")
	private Integer prestadorId;

	@Column(name = "orden")
	private Integer orden;

	@Column(name = "servicioid")
	private Integer servicioId;

	@Id
	@Column(name = "id")
	private Long reservaServicioId;

}
