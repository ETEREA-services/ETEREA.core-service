package eterea.api.rest.service;

import eterea.api.rest.exception.VoucherProductoNotFoundException;
import eterea.api.rest.model.VoucherProducto;
import eterea.api.rest.repository.IVoucherProductoRepository;
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
        return repository.findById(voucherProductoId).orElseThrow(() -> new VoucherProductoNotFoundException(voucherProductoId));
    }

}
