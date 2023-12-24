package eterea.core.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import eterea.core.api.rest.kotlin.model.Auditable;
import eterea.core.api.rest.kotlin.model.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "clientemovimientoprevio")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClienteMovimientoPrevio extends Auditable implements Serializable {

	private static final long serialVersionUID = -4005223853384447248L;

	@Id
	@Column(name = "clientemovimientoprevio_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clienteMovimientoPrevioId;

	private Byte enConstruccion;
	private Byte tieneArticulos;
	private Byte ticketImpreso;
	private Long clienteId;
	private Integer puntoVenta;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	private BigDecimal importe = BigDecimal.ZERO;

	@Column(name = "clientemovimiento_id")
	private Long clienteMovimientoId;

	@OneToOne(optional = true)
	@JoinColumn(name = "clienteId", insertable = false, updatable = false)
	private Cliente cliente;

	@OneToMany()
	@JoinColumn(name = "clientemovimientoprevio_id", insertable = false, updatable = false)
	private List<ArticuloMovimientoPrevio> articuloMovimientoPrevios;

}
