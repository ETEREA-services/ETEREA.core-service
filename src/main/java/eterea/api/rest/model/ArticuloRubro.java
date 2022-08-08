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
@Table(name = "articulosrubros")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloRubro extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -8470827828614569484L;

	@Id
	@Column(name = "codigo")
	private Long articuloRubroId;
	
	@Column(name = "aru_neg_id")
	private Integer negocioId;
	
	private String descripcion;
	
	@Column(name = "unegocio")
	private Integer unidadNegocio;
	
	@Column(name = "aru_restaurant")
	private Byte restaurant;
	
	@Column(name = "aru_prv_id")
	private Long proveedorId;
	
}
