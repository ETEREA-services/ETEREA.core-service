/**
 *
 */
package eterea.core.service.service;

import eterea.core.service.exception.GrupoException;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.dto.programadia.GrupoToDtoMapper;
import eterea.core.service.model.dto.programadia.ProductoToDtoMapper;
import eterea.core.service.model.dto.programadia.ProgramaDiaGrupoDto;
import eterea.core.service.model.dto.programadia.VentasPorGrupoDto;
import eterea.core.service.model.dto.programadia.VentasPorGrupoPorProveedorDto;
import eterea.core.service.repository.GrupoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author daniel
 */
@Service
public class GrupoService {

	private final GrupoRepository repository;
	private final GrupoProductoService grupoProductoService;
	private final VoucherService voucherService;
	private final VoucherProductoService voucherProductoService;
	private final ProveedorService proveedorService;
	private final GrupoToDtoMapper grupoToDtoMapper;
	private final ProductoService productoService;
	private final ArticuloService articuloService;
	private final ProductoToDtoMapper productoToDtoMapper;

	public GrupoService(GrupoRepository repository, GrupoProductoService grupoProductoService,
			VoucherService voucherService, VoucherProductoService voucherProductoService,
			ProveedorService proveedorService, GrupoToDtoMapper grupoToDtoMapper,
			ProductoService productoService, ArticuloService articuloService,
			ProductoToDtoMapper productoToDtoMapper) {
		this.repository = repository;
		this.grupoProductoService = grupoProductoService;
		this.voucherService = voucherService;
		this.voucherProductoService = voucherProductoService;
		this.proveedorService = proveedorService;
		this.grupoToDtoMapper = grupoToDtoMapper;
		this.productoService = productoService;
		this.articuloService = articuloService;
		this.productoToDtoMapper = productoToDtoMapper;
	}

	public List<Grupo> findAll() {
		return repository.findAll(Sort.by("nombre").ascending());
	}

	public List<ProgramaDiaGrupoDto> findAllWithProductos() {

		return repository.findAllWithProductos().stream()
				.map(grupo -> grupoToDtoMapper.toDto(grupo, new ArrayList<>(), new HashMap<>()))
				.toList();
	}

	public Grupo findById(Integer grupoId) {
		return repository.findById(grupoId).orElseThrow(() -> new GrupoException(grupoId));
	}

	public List<Grupo> findAllByVentaInternet(Byte habilitado) {
		return repository.findAllByVentaInternet(habilitado, Sort.by("nombre").ascending());
	}

	public Grupo update(Grupo newGrupo, Integer grupoId) {
		return repository.findById(grupoId).map(grupo -> {
			grupo.setNombre(newGrupo.getNombre());
			grupo.setVentaInternet(newGrupo.getVentaInternet());
			return repository.save(grupo);
		}).orElseThrow(() -> new GrupoException(grupoId));
	}

	public List<Grupo> findAllByVoucherFechaServicio(OffsetDateTime fechaServicio) {

		List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, false, false);
		List<VoucherProducto> vp = new ArrayList<>();
		for (Voucher v : vouchers) {
			vp.addAll(voucherProductoService.findAllByVoucherId(v.getVoucherId()));
		}
		List<GrupoProducto> grupos = new ArrayList<>();
		for (VoucherProducto element : vp) {
			grupos.addAll(grupoProductoService.findByProductoId(element.getProductoId()));
		}
		Set<Grupo> gruposSet = new HashSet<>();
		for (GrupoProducto element : grupos) {
			gruposSet.add(findById(element.getGrupoId()));
		}
		return new ArrayList<>(gruposSet);
	}

	public BigDecimal totalVentasByGrupoIdAndVoucherFechaServicio(Integer grupoId, OffsetDateTime fechaServicio) {
		return repository.totalVentasByGrupoIdAndVoucherFechaServicio(grupoId, fechaServicio);
	}

	// public List<VentasPorGrupoDto> getGruposVendidos(OffsetDateTime
	// fechaServicio) {
	// List<Grupo> grupos = findAllByVoucherFechaServicio(fechaServicio);
	// List<VentasPorGrupoDto> ventasPorGrupo = new ArrayList<>();
	// grupos.forEach(g -> {
	// BigDecimal totalVentas =
	// totalVentasByGrupoIdAndVoucherFechaServicio(g.getGrupoId(), fechaServicio);
	// List<Proveedor> proveedores =
	// proveedorService.findAllByGrupoIdAndVoucherFechaServicio(g.getGrupoId(),
	// fechaServicio);
	// List<VentasPorGrupoPorProveedorDto> ventasPorProveedor = new ArrayList<>();

	// proveedores.forEach(p -> {
	// BigDecimal totalVentasPorProveedor = proveedorService
	// .totalVentasByProveedorIdAndGrupoIdAndVoucherFechaServicio(p.getProveedorId(),
	// g.getGrupoId(),
	// fechaServicio);
	// ventasPorProveedor.add(new VentasPorGrupoPorProveedorDto(p.getRazonSocial(),
	// totalVentasPorProveedor));
	// });

	// ventasPorGrupo.add(new VentasPorGrupoDto(fechaServicio, g.getNombre(),
	// totalVentas, ventasPorProveedor));
	// });
	// return ventasPorGrupo;
	// }

	public List<VentasPorGrupoDto> getGruposVendidos(OffsetDateTime fechaServicio) {
		List<Grupo> grupos = findAllByVoucherFechaServicio(fechaServicio);
		List<VentasPorGrupoDto> ventasPorGrupo = new ArrayList<>();

		grupos.forEach(g -> {
			BigDecimal totalVentas = totalVentasByGrupoIdAndVoucherFechaServicio(g.getGrupoId(), fechaServicio);
			List<Proveedor> proveedores = proveedorService.findAllByGrupoIdAndVoucherFechaServicio(g.getGrupoId(),
					fechaServicio);
			List<VentasPorGrupoPorProveedorDto> ventasPorProveedor = new ArrayList<>();

			proveedores.forEach(p -> {
				ventasPorProveedor.addAll(
						proveedorService.findVentasPorGrupoPorProveedor(g.getGrupoId(), fechaServicio, p.getProveedorId()));
			});

			List<Producto> productos = productoService.findAllByGrupoId(g.getGrupoId());
			Map<Integer, List<Articulo>> articulosByProducto = productos.stream()
					.collect(Collectors.toMap(
							Producto::getProductoId,
							producto -> articuloService.findAllByProductoId(producto.getProductoId())));

			ventasPorGrupo.add(new VentasPorGrupoDto(
					fechaServicio,
					grupoToDtoMapper.toDto(g, productos, articulosByProducto),
					totalVentas,
					ventasPorProveedor));
		});

		return ventasPorGrupo;
	}

	public BigDecimal totalVentasByGrupoAndVoucher(Integer grupoId, Long voucherId) {
		return repository.totalVentasByGrupoIdAndVoucherId(grupoId, voucherId);
	}

	/*
	 * public List<Grupo> findByFechaServicio(String fecha) { List<Integer> lista =
	 * repository.findAllByFecha(ToolService.stringDDMMYYYY2OffsetDateTime(fecha));
	 * System.out.println(lista.size()); List<Grupo> grupos = new ArrayList<>(); for
	 * (Integer element : lista) { grupos.add(findById(element)); } return grupos; }

	 */
}
