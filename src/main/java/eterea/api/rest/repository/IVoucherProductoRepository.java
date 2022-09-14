package eterea.api.rest.repository;

import eterea.api.rest.model.VoucherProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVoucherProductoRepository extends JpaRepository<VoucherProducto, Long> {
     public List<VoucherProducto> findByVoucherId(Long voucherId);
}
