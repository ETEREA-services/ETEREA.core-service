/**
 * 
 */
package eterea.api.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Table(name = "clientegrupocupo", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "cliente_id", "grupo_id", "dias" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteGrupoCupo extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6836097066483666183L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clientegrupocupo_id")
	private Long clientegrupocupoId;

	@Column(name = "cliente_id")
	private Long clienteId;

	@Column(name = "grupo_id")
	private Integer grupoId;

	@Column(name = "dias")
	@NotNull
	private Integer dias = 0;

	@Column(name = "cantidad")
	@NotNull
	private Integer cantidad = 0;

}
