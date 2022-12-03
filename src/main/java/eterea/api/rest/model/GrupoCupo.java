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
@Table(name = "grupocupo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GrupoCupo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6632575124613200508L;

	@Id
	@Column(name = "grc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long grupoCupoid;
	
	@Column(name = "grc_gru_id")
	private Integer grupoId;
	
	@Column(name = "grc_desde")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime desde;
	
	@Column(name = "grc_hasta")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime hasta;
	
	@Column(name = "grc_cantidad")
	private Integer cantidad;
	
}
