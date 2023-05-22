/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
@Table(name = "legajoregistro", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "lre_leg_id", "lre_fecha", "lre_hora" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LegajoRegistro extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lre_id")
	private Long legajoRegistroId;

	@Column(name = "lre_leg_id")
	private Integer legajoId;

	@Column(name = "lre_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;

	@Column(name = "lre_hora")
	private Time hora;

	@Column(name = "lre_salida")
	private Byte salida;

	@Column(name = "lre_ori_id")
	private Integer origenId;

}
