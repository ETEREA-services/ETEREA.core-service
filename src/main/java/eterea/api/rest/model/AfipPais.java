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
@Table(name = "paisafip")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AfipPais extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7493795348076945340L;

	@Id
	@Column(name = "pais_id")
	private Integer afipPaisId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "auto_id")
	private Long autoId;
}
