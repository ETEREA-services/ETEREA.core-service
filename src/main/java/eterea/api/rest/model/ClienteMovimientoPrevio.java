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
@Table(name = "clientemovimientoprevio")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteMovimientoPrevio extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4005223853384447248L;

	@Id
	@Column(name = "clientemovimientoprevio_id")
	private Long clienteMovimientoPrevioId;
	
	@Column(name = "en_contruccion")
	private Byte enConstruccion;
	
	@Column(name = "tiene_articulos")
	private Byte articulos;
	
	@Column(name = "ticket_impreso")
	private Byte ticketImpreso;
	
	@Column(name = "cliente_id")
	private Long clienteId;
	
	@Column(name = "punto_venta")
	private Integer puntoVenta;
	
	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "importe")
	private BigDecimal importe = BigDecimal.ZERO;
	
	@Column(name = "clientemovimiento_id")
	private Long clienteMovimientoId;

}
