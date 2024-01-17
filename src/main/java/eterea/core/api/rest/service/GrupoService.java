/**
 *
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.GrupoException;
import eterea.core.api.rest.kotlin.model.Voucher;
import eterea.core.api.rest.kotlin.model.VoucherProducto;
import eterea.core.api.rest.model.Grupo;
import eterea.core.api.rest.model.GrupoProducto;
import eterea.core.api.rest.repository.IGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private IGrupoRepository repository;
	@Autowired
	private GrupoProductoService grupoProductoService;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private VoucherProductoService voucherProductoService;

	public List<Grupo> findAll() {
		return repository.findAll(Sort.by("nombre").ascending());
	}

	public Grupo findById(Integer grupoId) {
		return repository.findById(grupoId).orElseThrow(() -> new GrupoException(grupoId));
	}

	public List<Grupo> findAllByVentaInternet(Byte habilitado) {
		return repository.findAllByVentainternet(habilitado, Sort.by("nombre").ascending());
	}

	public Grupo update(Grupo newgrupo, Integer grupoId) {
		return repository.findById(grupoId).map(grupo -> {
			grupo.setNombre(newgrupo.getNombre());
			grupo.setVentainternet(newgrupo.getVentainternet());
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
