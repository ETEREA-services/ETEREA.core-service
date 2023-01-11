/**
 * Entidad vinculada a articuloscentro ...hay inconsistencia en la tabla un salto temoporal en
 * los registros y cambio en la nomenclatura al 19-05-2022, no registra un uso diario
 */
package eterea.api.rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "articuloscentro", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "arc_art_id", "arc_cst_id" }) })
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArticuloCentro extends Auditable implements Serializable {

	private static final long serialVersionUID = 71508028326850175L;

	@Id
	@Column(name = "arc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long articuloCentroId;

	@Column(name = "arc_art_id")
	private String articuloId;

	@Column(name = "arc_cst_id")
	private Integer centroStockId;

	@Column(name = "arc_saldo")
	private BigDecimal saldo = BigDecimal.ZERO;

	@Column(name = "arc_saldosf")
	private BigDecimal saldoStockFicha = BigDecimal.ZERO;

}
