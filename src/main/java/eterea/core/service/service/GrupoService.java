/**
 *
 */
package eterea.core.service.service;

import eterea.core.service.exception.GrupoException;
import eterea.core.service.kotlin.model.Grupo;
import eterea.core.service.kotlin.model.GrupoProducto;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.repository.GrupoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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

	public GrupoService(GrupoRepository repository, GrupoProductoService grupoProductoService, VoucherService voucherService, VoucherProductoService voucherProductoService) {
		this.repository = repository;
		this.grupoProductoService = grupoProductoService;
		this.voucherService = voucherService;
		this.voucherProductoService = voucherProductoService;
	}

	public List<Grupo> findAll() {
		return repository.findAll(Sort.by("nombre").ascending());
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

	/*
	 * public List<Grupo> findByFechaServicio(String fecha) { List<Integer> lista =
	 * repository.findAllByFecha(ToolService.stringDDMMYYYY2OffsetDateTime(fecha));
	 * System.out.println(lista.size()); List<Grupo> grupos = new ArrayList<>(); for
	 * (Integer element : lista) { grupos.add(findById(element)); } return grupos; }
	 */
}
