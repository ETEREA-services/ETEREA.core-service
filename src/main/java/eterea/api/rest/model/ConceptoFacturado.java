/**
 * Entidad relacionada a la tabla conceptosfact,
 *
 */
package eterea.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "conceptosfact")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ConceptoFacturado extends Auditable implements Serializable {

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
	private String concepto = "";

	@Column(name = "clavedetartic")
	private Long articuloMovimientoId;

	@OneToOne(optional = true)
	@JoinColumn(name = "clavemovclie", insertable = false, updatable = false)
	private ClienteMovimiento clienteMovimiento;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "clavedetartic", insertable = false, updatable = false)
	private ArticuloMovimiento articuloMovimiento;
	
}
