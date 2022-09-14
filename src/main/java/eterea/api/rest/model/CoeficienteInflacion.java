/**
 * Entidad de la tabla coeficiente_inflacion, ultimo registro el 21/01/2020
 * TODO: cambiar el nombre del atributo "anho" por anio o en su defecto year
 * TODO: revisar su uso o implementacion en el modelo
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table
@AllArgsConstructor
public class CoeficienteInflacion extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 3297333167839333107L;

	private Integer anho;
	private Integer mes;
	private BigDecimal coeficiente = BigDecimal.ZERO;

	@Id
	@Column(name = "coeficienteinflacion_id")
	private Long coeficienteInflacionId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		CoeficienteInflacion that = (CoeficienteInflacion) o;
		return coeficienteInflacionId != null && Objects.equals(coeficienteInflacionId, that.coeficienteInflacionId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
