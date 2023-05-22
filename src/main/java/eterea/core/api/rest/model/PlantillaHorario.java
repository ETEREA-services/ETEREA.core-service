/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "plantillahorario", uniqueConstraints = { @UniqueConstraint(columnNames = { "plh_pla_id", "plh_salida" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PlantillaHorario extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8670276869094062654L;

	@Column(name = "plh_pla_id")
	private Integer planillaId;
	
	@Column(name = "plh_salida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime salida;
	
	@Column(name = "plh_entrada")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime entrada;
	
	@Column(name = "plh_minutos")
	private Long minutos;
	
	@Id
	@Column(name = "plh_id")
	private Long plantillaHorarioId;
}
