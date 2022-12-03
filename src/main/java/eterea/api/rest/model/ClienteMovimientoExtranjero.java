package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "movclieex")
@AllArgsConstructor
public class ClienteMovimientoExtranjero extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = -7019052783240902949L;

	@Id
	@Column(name = "mce_id")
	private Long clienteMovimientoExtranjeroId;

	@Column(name = "mce_mcl_id")
	private Long clienteMovimientoId;

	@Column(name = "mce_aci_id")
	private Integer mce_aci_id;

	@Column(name = "mce_ait_id")
	private Integer mce_ait_id;

	@Column(name = "mce_incotermsds")
	private String mce_incotermsds;

	@Column(name = "mce_apa_id")
	private Integer mce_apa_id;

	@Column(name = "mce_aid_id")
	private Integer mce_aid_id;

	@Column(name = "mce_formapago")
	private String formaPago;

	@Column(name = "mce_obscomerc")
	private String observacionesComerciales;

	@Column(name = "mce_observ")
	private String observaciones;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ClienteMovimientoExtranjero that = (ClienteMovimientoExtranjero) o;
		return clienteMovimientoExtranjeroId != null && Objects.equals(clienteMovimientoExtranjeroId, that.clienteMovimientoExtranjeroId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
