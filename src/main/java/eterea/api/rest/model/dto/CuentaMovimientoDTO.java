/**
 * 
 */
package eterea.api.rest.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import eterea.api.rest.model.Comprobante;
import eterea.api.rest.model.Cuenta;
import eterea.api.rest.model.Negocio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaMovimientoDTO implements Serializable {

	private static final long serialVersionUID = -4421270621003208226L;

	private Long cuentaMovimientoId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	private Integer orden;
	private Integer item;
	private Byte debita = 0;
	private Integer negocioId;
	private Long numeroCuenta;
	private Integer comprobanteId;
	private String concepto;
	private BigDecimal importe = BigDecimal.ZERO;
	private Long subrubroId;
	private Long proveedorId;
	private Long clienteId;
	private Long cierreCajaId;
	private Integer nivel;
	private Long firma;
	private Integer tipoAsientoId;
	private Long articuloMovimientoId;
	private Integer ejercicioId;
	private Byte inflacion;

	private Cuenta cuenta;
	private Comprobante comprobante;
	private Negocio negocio;

}
