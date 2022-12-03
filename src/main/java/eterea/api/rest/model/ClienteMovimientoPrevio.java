package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "clientemovimientoprevio")
@AllArgsConstructor
public class ClienteMovimientoPrevio extends Auditable implements Serializable {

	@Serial
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ClienteMovimientoPrevio that = (ClienteMovimientoPrevio) o;
		return clienteMovimientoPrevioId != null && Objects.equals(clienteMovimientoPrevioId, that.clienteMovimientoPrevioId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
