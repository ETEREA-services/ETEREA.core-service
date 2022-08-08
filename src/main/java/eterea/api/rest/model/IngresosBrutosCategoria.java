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
@Table(name = "ibcategoria")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class IngresosBrutosCategoria extends Auditable implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2403143935746043523L;

	@Id
	@Column(name = "ibcategoria_id")
	private Integer ingresosBrutosCategoriaId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "user_id")
	private String userId;
}
