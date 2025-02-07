/**
 * 
 */
package eterea.core.service.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.model.dto.programadia.ProgramaDiaVentasDto;
import org.springframework.stereotype.Service;

import eterea.core.service.exception.ProveedorException;
import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.model.dto.programadia.ArticuloToDtoMapper;
import eterea.core.service.model.dto.programadia.ProgramaDiaProductoDto;
import eterea.core.service.model.dto.programadia.VentasPorGrupoPorProveedorDto;
import eterea.core.service.model.dto.VentaDto;
import eterea.core.service.repository.ProveedorRepository;

/**
 * @author daniel
 *
 */
@Service
public class ProveedorService {

	private final ProveedorRepository repository;
	private final ArticuloService articuloService;
	private final ProductoService productoService;
	private final ArticuloToDtoMapper articuloToDtoMapper;

	public ProveedorService(ProveedorRepository repository, ArticuloService articuloService, ArticuloToDtoMapper articuloToDtoMapper, ProductoService productoService) {
		this.repository = repository;
		this.articuloService = articuloService;
		this.productoService = productoService;
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
		List<ProgramaDiaVentasDto> ventas = repository.findVentasPorGrupoPorProveedor(grupoId, fechaServicio, proveedorId);
		
		// Get all product IDs from ventas
		List<Integer> productoIds = ventas.stream()
			.map(ProgramaDiaVentasDto::productoId)
			.distinct()
			.toList();
			
		// Get all products
		Map<Integer, Producto> productosMap = productoService.findAll().stream()
			.collect(Collectors.toMap(
				Producto::getProductoId,
				producto -> producto
			));
			
		// Get all articles for these products
		Map<Integer, List<Articulo>> articulosByProducto = productoIds.stream()
			.collect(Collectors.toMap(
				productoId -> productoId,
				productoId -> articuloService.findAllByProductoId(productoId)
			));

		// Combine all data
		return ventas.stream()
			.map(venta -> {
				Producto producto = productosMap.get(venta.productoId());
				List<Articulo> articulos = articulosByProducto.get(venta.productoId());
				
				return new VentasPorGrupoPorProveedorDto(
					venta.proveedorNombre(),
					venta.cantidad(),
					new ProgramaDiaProductoDto(
						producto.getProductoId(),
						producto.getNombre(),
						producto.getObservaciones(),
						articulos.stream()
							.map(articuloToDtoMapper)
							.toList()
					)
				);
			})
			.toList();
	}

}
