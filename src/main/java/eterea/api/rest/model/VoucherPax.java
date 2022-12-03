package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="voucherpax")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class VoucherPax extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -180655837853128821L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int voucherpaxId;
	
	private Integer voucherId;
	private String apellido;
	private String nombre;
	private Long documento;
	private Short edad;
	private Short alojado;
	private OffsetDateTime fechaServicio;
	private String telefono;
	private Short whatsapp;
    private Short titular;

}
