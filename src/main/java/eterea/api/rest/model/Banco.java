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
@Table(name = "banco")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Banco extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4578943377018812826L;

	@Id
	@Column(name = "ban_id")
	private Integer bancoId;
	
	@Column(name = "ban_nombre")
	private String nombre;
	
}
