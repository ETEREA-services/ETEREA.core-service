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
@Table(name = "secuenciaarticulo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SecuenciaArticulo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -833858526030702381L;

	@Id
	@Column(name = "clave")
	private Long secuenciaArticuloId;
	
	@Column(name = "sar_sec_id")
	private Integer secuenciaId;
	
	@Column(name = "sar_posicion")
	private Integer posicion;
	
	@Column(name = "sar_art_id")
	private String articuloId;
}
