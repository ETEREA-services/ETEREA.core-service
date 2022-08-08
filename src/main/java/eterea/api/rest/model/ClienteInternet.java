/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
@Table(name = "clienteinternet")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteInternet extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7817956435234734227L;

	@Id
	@Column(name = "cliente_id")
	private Long clienteId;

	@Column(name = "password")
	@Size(max = 64)
	private String password;

}
