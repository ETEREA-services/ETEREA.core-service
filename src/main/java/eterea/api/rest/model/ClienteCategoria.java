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
@Table(name = "clientecateg")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCategoria extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -2812112161256002721L;

	@Id
	@Column(name = "cca_id")
	private Integer clienteCategoriaId;
	
	@Column(name = "cca_nombre")
	private String nombre;

}
