/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "turistareintegro", uniqueConstraints = { @UniqueConstraint(columnNames = { "tipoId", "reserva" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TuristaReintegro extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -893254288741617707L;

	private Integer tipoId;
	private Long reserva;
	private Integer documentoId;
	private String numeroDocumento;
	private Integer paisId;
	private Integer condicionIva;
	private Integer relacionId;
	private Integer monedaId;
	private BigDecimal cotizacion = BigDecimal.ZERO;

	@Id
	private Long autoId;
}
