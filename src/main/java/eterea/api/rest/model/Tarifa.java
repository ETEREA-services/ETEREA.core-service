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
@Table(name = "tarifa")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -96358681618836257L;

	@Id
	@Column(name = "tar_id")
	private Integer tarifaId;
	
	@Column(name = "tar_nombre")
	private String nombre;
	
	@Column(name = "tar_art_id")
	private String articuloId;
}
