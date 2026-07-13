package eterea.core.service.hexagonal.stock.articulo.domain.ports.in;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.kotlin.model.VoucherProducto;
import java.util.List;

public interface FindArticulosByVoucherUseCase {
    List<Articulo> findAllByVoucher(List<VoucherProducto> voucherProductos);
}
