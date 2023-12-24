/**
 * Entidad de la tabla centrosstock
 */
package eterea.core.api.rest.model;

import eterea.core.api.rest.kotlin.model.Auditable;
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
@Table(name = "centrosstock")
@AllArgsConstructor
public class CentroStock extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 279145846728211613L;

	@Id
	@Column(name = "codigo")
	private Integer centroStockId;
	
	private String descripcion;
	
	@Column(name = "cst_neg_id")
	private Integer negocioId;
	
	@Column(name = "cst_contable")
	private Long cuenta;
	
	@Column(name = "cst_controlastock")
	private Byte controlaStock;
	
	@Column(name = "cst_tipo")
	private Integer tipo;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		CentroStock that = (CentroStock) o;
		return centroStockId != null && Objects.equals(centroStockId, that.centroStockId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
