package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.VoucherProductoException;
import eterea.core.api.rest.model.VoucherProducto;
import eterea.core.api.rest.repository.IVoucherProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherProductoService {
    @Autowired
    private IVoucherProductoRepository repository;

    public List<VoucherProducto> findByVoucherId(Long voucherId) {
        return repository.findByVoucherId(voucherId);
    }

    public VoucherProducto findById(Long voucherProductoId) {
        return repository.findById(voucherProductoId).orElseThrow(() -> new VoucherProductoException(voucherProductoId));
    }

}
