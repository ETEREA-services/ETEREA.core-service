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
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "empresa")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Empresa extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -7878849276350845468L;

	@Id
	@Column(name = "emp_id")
	private Integer empresaId;

	@Column(name = "emp_neg_id")
	private Integer negocioId;

	@Column(name = "nombre")
	private String razonSocial;

	@Column(name = "emp_fantasia")
	private String nombreFantasia;

	@Column(name = "domicilio")
	private String domicilio;

	@Column(name = "telf")
	private String telefono;

	@Column(name = "cuit")
	private String cuit;

	@Column(name = "ingbrutos")
	private String ingresosBrutos;

	@Column(name = "nroestablecimiento")
	private String numeroEstablecimiento;

	@Column(name = "sedetimbrado")
	private String sedeTimbrado;

	@Column(name = "inicioactividades")
	private String inicioActividades;

	@Column(name = "condicioniva")
	private String condicionIva;

	@Column(name = "unegocio")
	private Byte unidadNegocio;

	@Column(name = "emp_diainicial")
	private Integer diaInicial;

	@Column(name = "emp_neg_id_comision")
	private Integer negocioIdComision;

	@Column(name = "emp_conectaunif")
	private Byte conectaUnificado;

	@Column(name = "certificado")
	private String certificado;

}
