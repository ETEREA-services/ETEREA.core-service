/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import eterea.core.api.rest.kotlin.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "ejercicio")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Ejercicio extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6664184636635330490L;

	@Id
	@Column(name = "eje_id")
	private Integer ejercicioId;
	
	@Column(name = "eje_nombre")
	private String nombre;
	
	@Column(name = "eje_fechainicio")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaInicio;
	
	@Column(name = "eje_fechafin")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fechaFin;
	
	@Column(name = "eje_bloqueado")
	private Byte bloqueado;
	
	@Column(name = "orden_contable_resultado")
	private Integer ordenContableResultado;
	
	@Column(name = "orden_contable_bienes_uso")
	private Integer ordenContableBienesUso;
	
}
