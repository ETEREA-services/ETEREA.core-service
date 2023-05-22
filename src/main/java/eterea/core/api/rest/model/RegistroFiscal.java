/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
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
@Table(name = "registro_fiscal")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RegistroFiscal extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2730015796919964080L;

	private String tipoComprobante;
	private Integer puntoVenta;
	private Long numeroTeorico;
	private String data;
	private String direccionIp;
	private String user;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime date;
	
	@Id
	private Long autoId;
}
