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
@Table(name = "proveedores")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7808107985601669641L;

	@Id
	@Column(name = "codigo")
	private Integer proveedorId;
	
	@Column(name = "razon")
	private String razonSocial;
	
	@Column(name = "prv_neg_id")
	private Integer negocioId;
	
	private String cuit;
	private String domicilio;
	
	@Column(name = "tel")
	private String telefono;
	
	private String fax;
	private String email;
	private Integer posicion;
	private String celular;
	
	@Column(name = "nroinscib")
	private String numeroInscripcionIngresosBrutos;
	
	@Column(name = "ibcontribuyente_id")
	private Integer ingresosBrutosContribuyenteId;
	
	@Column(name = "prv_prc_id")
	private Integer proveedorCategoriaId;
	
	@Column(name = "ibcategoria_id")
	private Integer ingresosBrutosCategoriaId;
	
	@Column(name = "prv_rep_codigo")
	private Integer reparticionId;
	
	private Byte transporte;

}
