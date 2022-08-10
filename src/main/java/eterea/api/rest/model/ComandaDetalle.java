/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "comandadetalle")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ComandaDetalle extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4427433605946767395L;

	@Id
	@Column(name = "clave")
	private Long comandaDetalleId;
	
	@Column(name = "cod_com_id")
	private Long comandaId;
	
	@Column(name = "cod_item")
	private Integer item;
	
	@Column(name = "cod_art_id")
	private String articuloId;
	
	@Column(name = "cod_cantidad")
	private BigDecimal cantidad = BigDecimal.ZERO;
	
	@Column(name = "cod_preciounitario")
	private BigDecimal precioUnitario = BigDecimal.ZERO;
	
	@Column(name = "cod_usuario")
	private String usuario;
	
	@Column(name = "cod_timestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime timestamp;
	
}