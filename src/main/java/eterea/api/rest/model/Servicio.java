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
@Table(name = "servicio")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Servicio extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 7401219143242258638L;

	@Id
	@Column(name = "ser_id")
	private Integer servicioId;
	
	@Column(name = "ser_corto")
	private String corto;
	
	@Column(name = "ser_detalle")
	private String detalle;
}
