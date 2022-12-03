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
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "comanda")
@AllArgsConstructor
public class Comanda extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 8063710300018825646L;

	@Id
	@Column(name = "com_id")
	private Long comandaId;
	
	@Column(name = "com_descripcion")
	private String descripcion;
	
	@Column(name = "com_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "com_cerrada")
	private Byte cerrada;
	
	@Column(name = "com_impresa")
	private Byte impresa;
	
	@Column(name = "com_firmada")
	private Byte firmada;
	
	@Column(name = "com_cli_id")
	private Long clienteId;
	
	@Column(name = "com_mesa")
	private Integer mesa;
	
	@Column(name = "com_paxs")
	private Integer paxs;
	
	@Column(name = "com_leg_id")
	private Long legajoId;
	
	@Column(name = "com_moz_id")
	private Long mozoId;
	
	@Column(name = "com_tco_id")
	private Integer comprobanteId;
	
	@Column(name = "com_nrocomp")
	private Long numeroComprobante;
	
	@Column(name = "com_usuario")
	private String usuario;
	
	@Column(name = "com_timestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime timestamp;
	
	@Column(name = " com_cir_id")
	private Long cierreRestaurantId;
	
	@Column(name = "com_nivel")
	private Integer nivel;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Comanda comanda = (Comanda) o;
		return comandaId != null && Objects.equals(comandaId, comanda.comandaId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
