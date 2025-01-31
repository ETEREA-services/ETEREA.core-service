/**
 * 
 */
package eterea.core.service.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import eterea.core.service.exception.ProveedorException;
import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.repository.ProveedorRepository;

/**
 * @author daniel
 *
 */
@Service
public class ProveedorService {

	private final ProveedorRepository repository;

	public ProveedorService(ProveedorRepository repository) {
		this.repository = repository;
	}

	public Proveedor findByProveedorId(Integer proveedorId) {
		return repository.findByProveedorId(proveedorId).orElseThrow(() -> new ProveedorException(proveedorId));
	}

	public List<Proveedor> findAllByGrupoIdAndVoucherFechaServicio(Integer grupoId, OffsetDateTime fechaServicio) {
		return repository.findAllByGrupoIdAndVoucherFechaServicio(grupoId, fechaServicio);
	}

	public BigDecimal totalVentasByProveedorIdAndGrupoIdAndVoucherFechaServicio(Long proveedorId, Integer grupoId,
			OffsetDateTime fechaServicio) {
		return repository.totalVentasByProveedorIdAndGrupoIdAndVoucherFechaServicio(proveedorId, grupoId, fechaServicio);
	}

}
