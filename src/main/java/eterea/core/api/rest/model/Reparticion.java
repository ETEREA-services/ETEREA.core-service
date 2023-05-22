/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;

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
@Table
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Reparticion extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2736838225411365613L;

	@Id
	private Long clave;
	
	@Column(name = "rep_codigo")
	private Integer reparticionId;
	
	@Column(name = "rep_nombre")
	private String nombre;
	
	@Column(name = "rep_domicilio")
	private String domicilio;
	
	@Column(name = "rep_telefono")
	private String telefono;
	
	@Column(name = "rep_fax")
	private String fax;
	
	@Column(name = "rep_email")
	private String email;
	
	@Column(name = "rep_prv_codigo")
	private Integer proveedorId;

}
