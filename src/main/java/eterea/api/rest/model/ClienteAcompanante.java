/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "clienteacomp", uniqueConstraints = { @UniqueConstraint(columnNames = { "cliente_id", "nro_documento" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAcompanante extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -9052145349621042869L;

	@Id
	@Column(name = "cliente_id")
	private Long clienteId;
	
	@Column(name = "nro_documento")
	private BigDecimal numeroDocumento = BigDecimal.ZERO;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "auto_id")
	private Long clienteAcompanateId;
}
