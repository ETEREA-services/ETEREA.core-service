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
@Table(name = "origen")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Origen extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1827424406624555218L;

	@Id
	@Column(name = "ori_id")
	private Long origen;
	
	@Column(name = "ori_nombre")
	private String nombre;
}
