/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import eterea.api.rest.util.OffsetDateTimeDeserializer;
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
@Table
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RegistroFiscal extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 2730015796919964080L;

	@Id
	@Column(name = "auto_id")
	private Long registroFiscalId;

	@Column(name = "tipo_comprobante")
	private String letraComprobante;
	private Integer puntoVenta;
	private Long numeroTeorico;
	private String data;
	private String direccionIp;
	private String user;

	@Column(name = "date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	private OffsetDateTime fecha;

}
