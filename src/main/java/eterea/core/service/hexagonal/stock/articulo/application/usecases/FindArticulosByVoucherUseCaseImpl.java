package eterea.core.service.hexagonal.stock.articulo.application.usecases;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.in.FindArticulosByVoucherUseCase;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.out.ArticuloRepository;
import eterea.core.service.kotlin.model.ProductoArticulo;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.service.ProductoArticuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindArticulosByVoucherUseCaseImpl implements FindArticulosByVoucherUseCase {

    private final ArticuloRepository articuloRepository;
    private final ProductoArticuloService productoArticuloService;

    @Override
    public List<Articulo> findAllByVoucher(List<VoucherProducto> voucherProductos) {
        List<Articulo> articulos = new ArrayList<>();
        for (VoucherProducto voucherProducto : voucherProductos) {
            List<ProductoArticulo> productoArticulos = productoArticuloService.findAllByProductoId(voucherProducto.getProductoId());
            for (ProductoArticulo productoArticulo : productoArticulos) {
                articuloRepository.findByArticuloId(productoArticulo.getArticuloId())
                        .ifPresent(articulos::add);
            }
        }
        return articulos;
    }
}