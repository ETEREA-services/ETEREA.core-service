/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "ocompradet")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompraDetalle extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 648235482964401128L;

	@Id
	@Column(name = "ocd_id")
	private Long ordenCompraDetalleId;
	
	@Column(name = "ocd_oco_id")
	private Long ordenCompraId;
	
	@Column(name = "ocd_dea_id")
	private Long articuloMovimientoId;
	
	@Column(name = "ocd_rar_id")
	private Long reservaArticuloId;
	
	@Column(name = "ocd_art_id")
	private String articuloId;
	
	@Column(name = "ocd_cantidad")
	private BigDecimal cantidad = BigDecimal.ZERO;
	
	@Column(name = "ocd_preciocompra")
	private BigDecimal precioCompra = BigDecimal.ZERO;
	
	@Column(name = "ocd_compraneto")
	private BigDecimal compraNeto = BigDecimal.ZERO;
	
	@Column(name = "ocd_pendiente")
	private Byte pendiente;
	
	@Column(name = "ocd_mpr_id")
	private Long proveedorMovimientoId;
	
	@Column(name = "ocd_preciofacturaprov")
	private BigDecimal precioFacturaProveedor = BigDecimal.ZERO;
}
