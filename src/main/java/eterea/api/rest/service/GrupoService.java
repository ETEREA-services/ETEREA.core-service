/**
 *
 */
package eterea.api.rest.service;

import eterea.api.rest.exception.GrupoNotFoundException;
import eterea.api.rest.model.Grupo;
import eterea.api.rest.model.GrupoProducto;
import eterea.api.rest.model.Voucher;
import eterea.api.rest.model.VoucherProducto;
import eterea.api.rest.repository.IGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        return repository.findById(grupoId).orElseThrow(() -> new GrupoNotFoundException(grupoId));
    }

    public List<Grupo> findAllByVentaInternet(Byte habilitado) {
        return repository.findAllByVentainternet(habilitado, Sort.by("nombre").ascending());
    }

    public Grupo update(Grupo newgrupo, Integer grupoId) {
        return repository.findById(grupoId).map(grupo -> {
            grupo.setNombre(newgrupo.getNombre());
            grupo.setVentainternet(newgrupo.getVentainternet());
            return repository.save(grupo);
        }).orElseThrow(() -> new GrupoNotFoundException(grupoId));
    }

    public List<Grupo> findAllByVoucherFechaServicio(String fecha) {

        List<Voucher> vouchers = voucherService.findByFechaServicio(fecha);
        List<VoucherProducto> vp = new ArrayList<>();
        for (Voucher v : vouchers) {
            vp.addAll(voucherProductoService.findByVoucherId(v.getVoucherId()));
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
}

