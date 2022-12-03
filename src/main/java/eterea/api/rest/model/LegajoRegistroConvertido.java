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
@Table(name = "legajoregistroconv", uniqueConstraints = { @UniqueConstraint(columnNames = { "lrc_leg_id", "lrc_salida" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LegajoRegistroConvertido extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1114126858099585527L;

	@Column(name = "lrc_leg_id")
	private Long legajoId;
	
	@Column(name = "lrc_salida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime salida;
	
	@Column(name = "lrc_entrada")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime entrada;
	
	@Column(name = "lrc_minutos")
	private Long minutos;
	
	@Id
	@Column(name = "lrc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long legajoRegistroConvertidoId;

}
