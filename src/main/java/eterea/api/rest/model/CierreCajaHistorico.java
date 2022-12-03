/**
 * Entidad de la tabla cierrecajahist
 */
package eterea.api.rest.model;

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
@Table(name = "cierrecajahist")
@AllArgsConstructor
public class CierreCajaHistorico extends Auditable implements Serializable {

	private static final long serialVersionUID = 8527721699567101437L;

	@Id
	@Column(name = "cch_id")
	private Long cierreCajaHistoricoId;
	
	@Column(name = "cch_neg_id")
	private Integer negocioId;
	
	@Column(name = "cch_cic_id")
	private Long cierreCajaId;
	
	private Integer puntoVenta;
	
	@Column(name = "coh_tope")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime tope;
	
	@Column(name = "cch_fechahora")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaHora;
	
	@Column(name = "cch_usuario")
	private String usuario;
	
	@Column(name = "cch_nivel")
	private Integer nivel;
	
	@Column(name = "cch_dneg_id")
	private Integer negocioIdDesde;
	
	@Column(name = "cch_hneg_id")
	private Integer negocioIdHasta;
	
	@Column(name = "cch_tra_id")
	private Long transferenciaId;
	
	@Column(name = "cch_enviado")
	private Byte enviado;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		CierreCajaHistorico that = (CierreCajaHistorico) o;
		return cierreCajaHistoricoId != null && Objects.equals(cierreCajaHistoricoId, that.cierreCajaHistoricoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}