package eterea.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "comprobfaltante", uniqueConstraints = { @UniqueConstraint(columnNames = { "cfa_neg_id", "cfa_cmp_id", "cfa_fecha", "cfa_prefijo", "cfa_numero" }) })
@AllArgsConstructor
public class ComprobanteFaltante extends Auditable implements Serializable{

	@Serial
	private static final long serialVersionUID = -4704794534990511458L;

	@Id
	@Column(name = "cfa_neg_id")
	private Integer negocioId;
	
	@Column(name = "cfa_cmp_id")
	private Integer comprobanteId;
	
	@Column(name = "cfa_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "cfa_prefijo")
	private Integer prefijo;
	
	@Column(name = "cfa_numero")
	private Long numero;
	
	@Column(name = "cfa_id")
	private Long comprobanteFaltanteId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ComprobanteFaltante that = (ComprobanteFaltante) o;
		return negocioId != null && Objects.equals(negocioId, that.negocioId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
