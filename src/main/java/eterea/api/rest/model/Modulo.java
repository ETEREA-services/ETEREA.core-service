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
@Table(name = "modulo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Modulo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4735988834463668110L;

	@Id
	@Column(name = "mod_id")
	private Integer moduloId;
	
	@Column(name = "mod_nombre")
	private String nombre;
}
