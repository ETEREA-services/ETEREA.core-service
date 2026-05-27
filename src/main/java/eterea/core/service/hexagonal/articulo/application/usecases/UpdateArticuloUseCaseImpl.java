package eterea.core.service.hexagonal.articulo.application.usecases;

import eterea.core.service.hexagonal.articulo.application.exception.ArticuloException;
import eterea.core.service.hexagonal.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.articulo.domain.ports.in.UpdateArticuloUseCase;
import eterea.core.service.hexagonal.articulo.domain.ports.out.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateArticuloUseCaseImpl implements UpdateArticuloUseCase {

    private final ArticuloRepository articuloRepository;

    @Override
    public Articulo updateArticulo(String articuloId, Articulo newArticulo) {
        Articulo articulo = articuloRepository.findByArticuloId(articuloId)
                .orElseThrow(() -> new ArticuloException(articuloId));

        articulo.setNegocioId(newArticulo.getNegocioId());
        articulo.setDescripcion(newArticulo.getDescripcion());
        articulo.setLeyendaVoucher(newArticulo.getLeyendaVoucher());
        articulo.setPrecioVentaSinIva(newArticulo.getPrecioVentaSinIva());
        articulo.setPrecioVentaConIva(newArticulo.getPrecioVentaConIva());
        articulo.setNumeroCuentaVentas(newArticulo.getNumeroCuentaVentas());
        articulo.setNumeroCuentaCompras(newArticulo.getNumeroCuentaCompras());
        articulo.setNumeroCuentaGastos(newArticulo.getNumeroCuentaGastos());
        articulo.setCentroStockId(newArticulo.getCentroStockId());
        articulo.setRubroId(newArticulo.getRubroId());
        articulo.setSubRubroId(newArticulo.getSubRubroId());
        articulo.setPrecioCompra(newArticulo.getPrecioCompra());
        articulo.setIva105(newArticulo.getIva105());
        articulo.setPrecioCompraNeto(newArticulo.getPrecioCompraNeto());
        articulo.setExento(newArticulo.getExento());
        articulo.setStockMinimo(newArticulo.getStockMinimo());
        articulo.setStockOptimo(newArticulo.getStockOptimo());
        articulo.setBloqueoCompras(newArticulo.getBloqueoCompras());
        articulo.setBloqueoStock(newArticulo.getBloqueoStock());
        articulo.setBloqueoVentas(newArticulo.getBloqueoVentas());
        articulo.setUnidadMedidaId(newArticulo.getUnidadMedidaId());
        articulo.setConDecimales(newArticulo.getConDecimales());
        articulo.setVentas(newArticulo.getVentas());
        articulo.setCompras(newArticulo.getCompras());
        articulo.setUnidadMedida(newArticulo.getUnidadMedida());
        articulo.setConversionId(newArticulo.getConversionId());
        articulo.setVentaSinStock(newArticulo.getVentaSinStock());
        articulo.setControlaStock(newArticulo.getControlaStock());
        articulo.setAsientoCostos(newArticulo.getAsientoCostos());
        articulo.setMascaraBalanza(newArticulo.getMascaraBalanza());
        articulo.setHabilitaIngreso(newArticulo.getHabilitaIngreso());
        articulo.setComision(newArticulo.getComision());
        articulo.setPrestadorId(newArticulo.getPrestadorId());
        articulo.setAutoNumericoId(newArticulo.getAutoNumericoId());

        return articuloRepository.save(articulo);
    }
}
