/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "articulohabil", uniqueConstraints = { @UniqueConstraint(columnNames = { "aha_fecha", "aha_serie" , "aha_inicial" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloHabilitado extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -9089343259469839172L;
	
	@Column(name = "aha_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaHabilitacion;
	
	@Column(name = "aha_serie")
	private String serie;
	
	@Column(name = "aha_inicial")
	private Long numeroInicial;
	
	@Column(name = "aha_final")
	private Long numeroFinal;
	
	@Column(name = "aha_art_id")
	private String articuloId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "aha_id")
	private Long articuloHabilitadoId;
	
}
