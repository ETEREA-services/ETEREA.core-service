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
@Table(name = "mostrador")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Mostrador extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 885664274444185076L;

	@Id
	private Integer mostradorId;
	
	private String nombre;
	private Integer productorIdMayor;
	private Integer productoIdMenor;
	private Integer productoIdLibre;
	private Integer productoIdChofer;
	
}
