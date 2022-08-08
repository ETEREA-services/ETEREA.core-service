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
@Table(name = "personal")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Personal extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 341366901340475861L;

	@Id
	@Column(name = "codigo")
	private Long legajoId;
	
	private String cuit;
	private String razon;
	private String domicilio;
	
	@Column(name = "tel")
	private String telefono;
	
	private String fax;
	private String email;
	private String celular;
	private Integer posicion;
	private Long constante;
	
	@Column(name = "cgocentro")
	private Integer centroStockId;
	
	@Column(name = "clave")
	private Long personalId;

}
