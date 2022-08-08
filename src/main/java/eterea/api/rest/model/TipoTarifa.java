/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

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
@Table(name = "tipotarifa")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TipoTarifa extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 7396734317059505078L;

	@Id
	private Integer tipoId;
	private String nombre;
	
}
