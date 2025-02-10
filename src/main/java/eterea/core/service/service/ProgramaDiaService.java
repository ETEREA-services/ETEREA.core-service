package eterea.core.service.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.kotlin.model.Grupo;
import eterea.core.service.model.dto.programadia.ProductoToDtoMapper;
import eterea.core.service.model.dto.programadia.ProgramaDiaDetallesDto;
import eterea.core.service.model.dto.programadia.mapper.ProveedorToDtoMapper;
import eterea.core.service.model.dto.programadia.mapper.ReservaToDtoMapper;
import eterea.core.service.model.dto.programadia.mapper.VoucherToDtoMapper;
import eterea.core.service.model.dto.programadia.GrupoToDtoMapper;
import eterea.core.service.model.dto.programadia.VentasPorGrupoDto;




@Service
public class ProgramaDiaService {

   private final VoucherService voucherService;
   private final ProductoService productoService;
   private final ArticuloService articuloService;
   private final VoucherProductoService voucherProductoService;
   private final GrupoService grupoService;
   private final VoucherToDtoMapper voucherToDtoMapper;
   private final ReservaToDtoMapper reservaToDtoMapper;
   private final ProveedorToDtoMapper proveedorToDtoMapper;
   private final ProductoToDtoMapper productoToDtoMapper;
   private final GrupoToDtoMapper grupoToDtoMapper;


   public ProgramaDiaService(VoucherService voucherService, VoucherToDtoMapper voucherToDtoMapper,
         ReservaToDtoMapper reservaToDtoMapper, ProveedorToDtoMapper proveedorToDtoMapper,
         ProductoService productoService, ArticuloService articuloService,
         ProductoToDtoMapper productoToDtoMapper, VoucherProductoService voucherProductoService,
         GrupoService grupoService, GrupoToDtoMapper grupoToDtoMapper) {


      this.voucherService = voucherService;
      this.voucherToDtoMapper = voucherToDtoMapper;
      this.reservaToDtoMapper = reservaToDtoMapper;
      this.proveedorToDtoMapper = proveedorToDtoMapper;
      this.productoService = productoService;
      this.articuloService = articuloService;
      this.productoToDtoMapper = productoToDtoMapper;
      this.voucherProductoService = voucherProductoService;
      this.grupoService = grupoService;
      this.grupoToDtoMapper = grupoToDtoMapper;
   }



   public List<ProgramaDiaDetallesDto> getProgramaDiaDetalles(OffsetDateTime fechaServicio) {
      List<ProgramaDiaDetallesDto> programas = new ArrayList<>();
      List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, false, false);

      vouchers.forEach(voucher -> {
         Reserva reserva = voucher.getReserva();
         Proveedor proveedor = voucher.getProveedor();
         List<VoucherProducto> voucherProductos = voucherProductoService.findAllByVoucherId(voucher.getVoucherId());
         Map<Integer, List<Articulo>> articulosByProducto = voucherProductos.stream()
               .collect(Collectors.toMap(
                     voucherProducto -> voucherProducto.getProducto().getProductoId(),
                     voucherProducto -> articuloService.findAllByProductoId(voucherProducto.getProducto().getProductoId())));
         // List<Producto> productos = productoService.findAllByVoucherId(voucher.getVoucherId());
         // Map<Integer, List<Articulo>> articulosByProducto = productos.stream()
         //       .collect(Collectors.toMap(
         //             Producto::getProductoId,
         //             producto -> articuloService.findAllByProductoId(producto.getProductoId())));
         List<Grupo> grupos = grupoService.findAllByVoucherFechaServicio(fechaServicio);
         List<VentasPorGrupoDto> ventasPorGrupo = grupos.stream()
               .map(g -> {
                  List<Producto> productos = productoService.findAllByGrupoId(g.getGrupoId());
                  Map<Integer, List<Articulo>> artsByProducto = productos.stream()
                        .collect(Collectors.toMap(
                              Producto::getProductoId,
                              producto -> articuloService.findAllByProductoId(producto.getProductoId())));
                  BigDecimal totalVentas = grupoService.totalVentasByGrupoAndVoucher(g.getGrupoId(), voucher.getVoucherId());

                  if (totalVentas == null || totalVentas.compareTo(BigDecimal.ZERO) == 0) {
                     return null;
                  }
                  return new VentasPorGrupoDto(fechaServicio, grupoToDtoMapper.toDto(g, productos, articulosByProducto), totalVentas, null);
               })
               .filter(Objects::nonNull)
               .toList();

         programas.add(new ProgramaDiaDetallesDto(
               voucher.getFechaServicio(),
               voucherToDtoMapper.toDto(voucher, voucherProductos, articulosByProducto),
               reservaToDtoMapper.apply(reserva),
               proveedorToDtoMapper.apply(proveedor),
               ventasPorGrupo));
      });
      return programas;
   }
}
