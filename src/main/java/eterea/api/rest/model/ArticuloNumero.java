/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "articulonumero", uniqueConstraints = { @UniqueConstraint(columnNames = { "arn_art_id", "arn_serie" , "arn_numero" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloNumero extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 966736326600248438L;

	@Column(name = "arn_art_id")
	private String articuloId;
	
	@Column(name = "arn_serie")
	private String serie;
	
	@Column(name = "arn_numero")
	private String numero;
	
	@Column(name = "arn_estado")
	private String estado;
	
	@Column(name = "arn_fecha")
	private String fecha;
	
	@Id
	@Column(name = "arn_id")
	private Long articuloNumeroId;
	
}
