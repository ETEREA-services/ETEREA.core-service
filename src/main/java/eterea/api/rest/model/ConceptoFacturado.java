/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name = "conceptosfact")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ConceptoFacturado extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8264376062048795530L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clave")
	private Long conceptoFacturadoId;

	@Column(name = "clavemovclie")
	private Long clienteMovimientoId;

	@Column(name = "nrolinea")
	private Integer numeroLinea;

	@Column(name = "concepto")
	@NotNull
	@Size(max = 240)
	private String concepto = "";

	@Column(name = "clavedetartic")
	private Long articuloMovimientoId;

}
