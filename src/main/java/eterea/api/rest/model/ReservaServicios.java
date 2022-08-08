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
@Table(name = "reservaservicios")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReservaServicios extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1521193795356726936L;

	@Column(name = "reservaid")
	private Long reservaId;
	
	@Column(name = "prestadorid")
	private Integer prestadorId;
	
	@Column(name = "servicios")
	private String servicios;
	
	@Id
	@Column(name = "id")
	private Long reservaServiciosId;
}
