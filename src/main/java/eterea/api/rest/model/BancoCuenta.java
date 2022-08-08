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
@Table(name = "bancocuenta", uniqueConstraints = { @UniqueConstraint(columnNames = { "bcu_ban_id", "bcu_cuenta" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BancoCuenta extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3385001732647319275L;

	@Column(name = "bcu_ban_id")
	private Integer bancoId;
	
	@Column(name = "bcu_cuenta")
	private String cuenta;
	
	@Column(name = "bcu_neg_id")
	private Integer negocioId;
	
	@Column(name = "bcu_titular")
	private String titular;
	
	@Column(name = "bcu_cbu")
	private String cbu;
	
	@Id
	@Column(name = "clave")
	private Integer bancoCuentaId;
	
}
