/**
 * Entidad de la tabla cierrecaja, relacionado con puntodeventa y con la tabla negocio
 */
package eterea.core.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "cierrecaja")
@AllArgsConstructor
public class CierreCaja extends Auditable implements Serializable {

	private static final long serialVersionUID = -3176171648818295644L;

	@Id
	@Column(name = "cic_id")
	private Long cierreCajaId;
	
	private Integer puntoVenta;
	
	@Column(name = "cic_neg_id")
	private Integer negocioId;
	
	@Column(name = "cic_tope")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime tope;
	
	@Column(name = "cic_fechahora")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaHora;
	
	private String usuario;
	
	@Column(name = "cic_nivel")
	private Integer nivel;
	
	@Column(name = "cic_dneg_id")
	private Integer negocioIdDesde;
	
	@Column(name = "cic_hneg_id")
	private Integer negocioIdHasta;
	
	@Column(name = "cic_tra_id")
	private Long transferenciaId;
	
	@Column(name = "cic_enviado")
	private Byte enviado;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		CierreCaja that = (CierreCaja) o;
		return cierreCajaId != null && Objects.equals(cierreCajaId, that.cierreCajaId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
