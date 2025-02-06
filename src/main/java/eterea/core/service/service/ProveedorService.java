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
import eterea.core.service.model.dto.programadia.ArticuloToDtoMapper;
import eterea.core.service.model.dto.programadia.ProgramaDiaArticuloDto;
import eterea.core.service.model.dto.programadia.ProgramaDiaProductoDto;
import eterea.core.service.model.dto.programadia.VentasPorGrupoPorProveedorDto;
import eterea.core.service.repository.ProveedorRepository;

/**
 * @author daniel
 *
 */
@Service
public class ProveedorService {

	private final ProveedorRepository repository;
	private final ArticuloService articuloService;
	private final ArticuloToDtoMapper articuloToDtoMapper;

	public ProveedorService(ProveedorRepository repository, ArticuloService articuloService, ArticuloToDtoMapper articuloToDtoMapper) {
		this.repository = repository;
		this.articuloService = articuloService;
		this.articuloToDtoMapper = articuloToDtoMapper;
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

	public List<VentasPorGrupoPorProveedorDto> findVentasPorGrupoPorProveedor(Integer grupoId, OffsetDateTime fechaServicio, Long proveedorId) {
		List<VentasPorGrupoPorProveedorDto> ventas = repository.findVentasPorGrupoPorProveedor(grupoId, fechaServicio, proveedorId);
		return ventas.stream()
				.map(v -> {
					ProgramaDiaProductoDto producto = v.producto();
					List<ProgramaDiaArticuloDto> articulos = articuloService.findAllByProductoId(producto.productoId()).stream()
							.map(articuloToDtoMapper)
							.toList();


					ProgramaDiaProductoDto productoWithArticulos = new ProgramaDiaProductoDto(
						producto.productoId(),
						producto.productoNombre(),
						producto.productoObs(),
						articulos
					);

					return new VentasPorGrupoPorProveedorDto(
						v.proveedorNombre(),
						v.cantidad(),
						productoWithArticulos
					);
				})
				.toList();
	}

}
