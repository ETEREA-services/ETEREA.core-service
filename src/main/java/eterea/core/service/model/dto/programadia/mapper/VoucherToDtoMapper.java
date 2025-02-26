package eterea.core.service.model.dto.programadia.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.model.dto.programadia.ProductoToDtoMapper;
import eterea.core.service.model.dto.programadia.VoucherDto;
import eterea.core.service.model.dto.programadia.VoucherProductoDto;

@Component
public class VoucherToDtoMapper {
   private final ProductoToDtoMapper productoToDtoMapper;

   public VoucherToDtoMapper(ProductoToDtoMapper productoToDtoMapper) {
      this.productoToDtoMapper = productoToDtoMapper;
   }

   public VoucherDto toDto(Voucher voucher, List<VoucherProducto> voucherProductos,
         Map<Integer, List<Articulo>> articulosByProducto) {
      return new VoucherDto(
            voucher.getVoucherId(),
            voucher.getNombrePax(),
            voucher.getSubeEn(),
            voucher.getObservaciones(),
            voucher.getConfirmado() == 1,
            voucher.getPagaCacheuta() == 1,
            voucher.getContacto(),
            voucher.getPaxs(),
            voucher.getPaxsReales(),
            voucher.getReservaId(),
            voucher.getNumeroVoucher(),
            voucherProductos.stream()
                  .map(vp -> new VoucherProductoDto(vp.getCantidadPaxs(),
                        productoToDtoMapper.toDto(vp.getProducto(),
                              articulosByProducto.getOrDefault(vp.getProducto().getProductoId(), List.of()))))
                  .collect(Collectors.toList()));
   }
}
