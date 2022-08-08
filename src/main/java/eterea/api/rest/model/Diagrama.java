/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "diagrama", uniqueConstraints = { @UniqueConstraint(columnNames = { "dia_leg_id", "dia_salida" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Diagrama extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3470227945963646577L;

	@Column(name = "dia_leg_id")
	private Long legajoId;
	
	@Column(name = "dia_salida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime salida;
	
	@Column(name = "dia_entrada")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime entrada;
	
	@Column(name = "dia_minutos")
	private Long minutos;
	
	@Id
	@Column(name = "dia_id")
	private Long diagramaId;
	
}
