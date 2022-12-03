/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
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
 * @author alma
 *
 */
@Data
@Entity
@Table(name = "diagramaregistro", uniqueConstraints = { @UniqueConstraint(columnNames = { "dir_leg_id", "dir_salida" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DiagramaRegistro extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1242002916555104318L;

	@Column(name = "dir_leg_id")
	private Long legajoId;
	
	@Column(name = "dir_salida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime salida;
	
	@Column(name = "dir_entrada")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime entrada;
	
	@Column(name = "dir_minutos")
	private Long minutos;
	
	@Column(name = "dir_tih_id")
	private Integer tipoHoraId;
	
	@Id
	@Column(name = "dir_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long diagramaRegistroId;
	
}
