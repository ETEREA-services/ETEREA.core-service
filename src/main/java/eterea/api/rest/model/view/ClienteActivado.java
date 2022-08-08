/**
 * 
 */
package eterea.api.rest.model.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Immutable
@Table(name = "vw_clienteactivado")
@NoArgsConstructor
@AllArgsConstructor
public class ClienteActivado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5344000813780494569L;

	@Id
	@Column(name = "cliente_id")
	private Long clienteId;

	@Column(name = "nombre_fantasia")
	private String nombreFantasia;

	@Column(name = "razon_social")
	private String razonSocial;

	@Column(name = "nombre")
	private String nombre;

}
