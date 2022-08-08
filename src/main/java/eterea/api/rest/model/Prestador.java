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
@Table(name = "prestador")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Prestador extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6268369828942305068L;

	@Id
	@Column(name = "pre_id")
	private Integer prestadorId; 
	
	@Column(name = "pre_nombre")
	private String nombre;
}
