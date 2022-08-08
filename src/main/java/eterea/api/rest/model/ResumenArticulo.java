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
@Table(name = "resumenarticulo", uniqueConstraints = { @UniqueConstraint(columnNames = { "rar_cuenta", "rar_ctacte", "rar_art_id", "rar_desde", "rar_hasta" }) })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ResumenArticulo extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4407422086962837602L;

	@Id
	@Column(name = "rar_cuenta")
	private Long cuenta;
	
	@Column(name = "rar_ctacte")
	private Byte cuentaCorriente;
	
	@Column(name = "rar_art_id")
	private String articuloId;
	
	@Column(name = "rar_desde")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime desde;
	
	@Column(name = "rar_hasta")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime hasta;
	
	@Column(name = "rar_cantidad")
	private BigDecimal cantidad = BigDecimal.ZERO;
	
	@Column(name = "rar_preciounitario")
	private BigDecimal precioUnitario = BigDecimal.ZERO;
	
	@Column(name = "rar_total")
	private BigDecimal total = BigDecimal.ZERO;
	
	@Column(name = "rar_aleatorio")
	private BigDecimal aleatorio = BigDecimal.ZERO;
	
	@Column(name = "rar_totalaleatorio")
	private BigDecimal totalAleatorio = BigDecimal.ZERO;
	
	@Column(name = "rar_eliminar")
	private Byte eliminar;
}
