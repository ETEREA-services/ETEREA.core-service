package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.model.dto.programadia.ProductoToDtoMapper;
import eterea.core.service.model.dto.programadia.ProgramaDiaDetallesDto;
import eterea.core.service.model.dto.programadia.mapper.ProveedorToDtoMapper;
import eterea.core.service.model.dto.programadia.mapper.ReservaToDtoMapper;
import eterea.core.service.model.dto.programadia.mapper.VoucherToDtoMapper;

@Service
public class ProgramaDiaService {

   private final VoucherService voucherService;
   private final ProductoService productoService;
   private final ArticuloService articuloService;
   private final VoucherToDtoMapper voucherToDtoMapper;
   private final ReservaToDtoMapper reservaToDtoMapper;
   private final ProveedorToDtoMapper proveedorToDtoMapper;
   private final ProductoToDtoMapper productoToDtoMapper;

   public ProgramaDiaService(VoucherService voucherService, VoucherToDtoMapper voucherToDtoMapper,
         ReservaToDtoMapper reservaToDtoMapper, ProveedorToDtoMapper proveedorToDtoMapper,
         ProductoService productoService, ArticuloService articuloService,
         ProductoToDtoMapper productoToDtoMapper) {
      this.voucherService = voucherService;
      this.voucherToDtoMapper = voucherToDtoMapper;
      this.reservaToDtoMapper = reservaToDtoMapper;
      this.proveedorToDtoMapper = proveedorToDtoMapper;
      this.productoService = productoService;
      this.articuloService = articuloService;
      this.productoToDtoMapper = productoToDtoMapper;
   }

   public List<ProgramaDiaDetallesDto> getProgramaDiaDetalles(OffsetDateTime fechaServicio) {
      List<ProgramaDiaDetallesDto> programas = new ArrayList<>();
      List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, false, false);

      vouchers.forEach(voucher -> {
         Reserva reserva = voucher.getReserva();
         Proveedor proveedor = voucher.getProveedor();
         List<Producto> productos = productoService.findAllByVoucherId(voucher.getVoucherId());
         Map<Integer, List<Articulo>> articulosByProducto = productos.stream()
               .collect(Collectors.toMap(
                     Producto::getProductoId,
                     producto -> articuloService.findAllByProductoId(producto.getProductoId())));

         programas.add(new ProgramaDiaDetallesDto(
               voucher.getFechaServicio(),
               voucherToDtoMapper.apply(voucher),
               reservaToDtoMapper.apply(reserva),
               proveedorToDtoMapper.apply(proveedor),
               productos.stream()
                     .map(p -> productoToDtoMapper.toDto(
                           p,
                           articulosByProducto.getOrDefault(p.getProductoId(), List.of())))
                     .collect(Collectors.toList())));
      });

      return programas;
   }
}
