/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "ficalred")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class FiscalRed extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4899350532913902825L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fiscalRedId;
	
	@Column(name = "ipaddress")
	private String ipAddress;
	
	@Column(name = "hwnd")
	private Long handler;
	
	@Column(name = "cgocliente")
	private Long clienteId;
	
	@Column(name = "cgocomprobante")
	private Long comprobanteId;
	
	@Column(name = "tipocomprob")
	private String tipoComprobante;
	
	@Column(name = "nrocomprob")
	private Long numeroComprobante;
	
	@Column(name = "ptoventa")
	private Integer puntoVenta;
	
	private Byte ok;
	private Byte imprimiendo;
	private Byte pendiente;
	private Byte procesada;
	
}
