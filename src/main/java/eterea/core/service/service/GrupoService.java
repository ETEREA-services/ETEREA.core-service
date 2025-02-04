/**
 *
 */
package eterea.core.service.service;

import eterea.core.service.exception.GrupoException;
import eterea.core.service.kotlin.model.Grupo;
import eterea.core.service.kotlin.model.GrupoProducto;
import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.model.dto.programadia.VentasPorGrupoDto;
import eterea.core.service.model.dto.programadia.VentasPorGrupoPorProveedor;
import eterea.core.service.repository.GrupoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public GrupoService(GrupoRepository repository, GrupoProductoService grupoProductoService,
			VoucherService voucherService, VoucherProductoService voucherProductoService, ProveedorService proveedorService) {
		this.repository = repository;
		this.grupoProductoService = grupoProductoService;
		this.voucherService = voucherService;
		this.voucherProductoService = voucherProductoService;
		this.proveedorService = proveedorService;
	}

	public List<Grupo> findAll() {
		return repository.findAll(Sort.by("nombre").ascending());
	}

	public List<Grupo> findAllWithProductos() {
		return repository.findAllWithProductos();
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

	public List<VentasPorGrupoDto> getGruposVendidos(OffsetDateTime fechaServicio) {
		List<Grupo> grupos = findAllByVoucherFechaServicio(fechaServicio);
		List<VentasPorGrupoDto> ventasPorGrupo = new ArrayList<>();
		grupos.forEach(g -> {
			BigDecimal totalVentas = totalVentasByGrupoIdAndVoucherFechaServicio(g.getGrupoId(), fechaServicio);
			List<Proveedor> proveedores = proveedorService.findAllByGrupoIdAndVoucherFechaServicio(g.getGrupoId(),
					fechaServicio);
			List<VentasPorGrupoPorProveedor> ventasPorProveedor = new ArrayList<>();

			proveedores.forEach(p -> {
				BigDecimal totalVentasPorProveedor = proveedorService
						.totalVentasByProveedorIdAndGrupoIdAndVoucherFechaServicio(p.getProveedorId(), g.getGrupoId(),
								fechaServicio);
				ventasPorProveedor.add(new VentasPorGrupoPorProveedor(p.getRazonSocial(), totalVentasPorProveedor));
			});

			ventasPorGrupo.add(new VentasPorGrupoDto(fechaServicio, g.getNombre(), totalVentas, ventasPorProveedor));
		});
		return ventasPorGrupo;
	}

	/*
	 * public List<Grupo> findByFechaServicio(String fecha) { List<Integer> lista =
	 * repository.findAllByFecha(ToolService.stringDDMMYYYY2OffsetDateTime(fecha));
	 * System.out.println(lista.size()); List<Grupo> grupos = new ArrayList<>(); for
	 * (Integer element : lista) { grupos.add(findById(element)); } return grupos; }
	 */
}
