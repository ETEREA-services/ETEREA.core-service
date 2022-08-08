/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "articulofecha", uniqueConstraints = { @UniqueConstraint(columnNames = { "articulo_id", "fecha" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloFecha extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1545964556063591559L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articulofecha_id")
	private Long articulofechaId;

	@Column(name = "articulo_id")
	@Size(max = 20)
	@NotNull
	private String articuloId = "";

	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "precio_usd")
	@NotNull
	private BigDecimal precioUsd = new BigDecimal(0);

	@Column(name = "precio_ars")
	@NotNull
	private BigDecimal precioArs = new BigDecimal(0);

}
