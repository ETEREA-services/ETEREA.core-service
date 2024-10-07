package eterea.core.service.service;

import eterea.core.service.exception.ProductoArticuloException;
import eterea.core.service.exception.VoucherProductoException;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.kotlin.repository.VoucherProductoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VoucherProductoService {

    private final VoucherProductoRepository repository;
    private final ProductoArticuloService productoArticuloService;

    public VoucherProductoService(VoucherProductoRepository repository, ProductoArticuloService productoArticuloService) {
        this.repository = repository;
        this.productoArticuloService = productoArticuloService;
    }

    public List<VoucherProducto> findAllByVoucherId(Long voucherId) {
        return repository.findAllByVoucherId(voucherId);
    }

    public VoucherProducto findByVoucherProductoId(Long voucherProductoId) {
        return repository.findByVoucherProductoId(voucherProductoId).orElseThrow(() -> new VoucherProductoException(voucherProductoId));
    }

    public VoucherProducto findByArticuloId(Long voucherId, String articuloId) {
        for (VoucherProducto voucherProducto : findAllByVoucherId(voucherId)) {
            try {
                productoArticuloService.findByProductoIdAndArticuloId(voucherProducto.getProductoId(), articuloId);
                return voucherProducto;
            } catch (ProductoArticuloException e) {
                log.debug("Following");
            }
        }
        return null;
    }

    public VoucherProducto add(VoucherProducto voucherProducto) {
        return repository.save(voucherProducto);
    }

    public List<VoucherProducto> saveAll(List<VoucherProducto> voucherProductos) {
        return repository.saveAll(voucherProductos);
    }

    @Transactional
    public void deleteAllByVoucherId(Long voucherId) {
        repository.deleteAllByVoucherId(voucherId);
    }

}
