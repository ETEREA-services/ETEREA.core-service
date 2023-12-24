/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

import eterea.core.api.rest.kotlin.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "articulosunegocio")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloUnidadNegocio extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 7563123526280129869L;

	@Id
	@Column(name = "codigo")
	private Integer articulo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "aun_neg_id")
	private Integer negocioId;
	
}
