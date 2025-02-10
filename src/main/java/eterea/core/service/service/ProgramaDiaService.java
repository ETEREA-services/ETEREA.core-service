package eterea.core.service.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
      // Get all vouchers first
      List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, false, false);
      if (vouchers.isEmpty()) {
          return new ArrayList<>();
      }

      // Get all voucher IDs
      List<Long> voucherIds = vouchers.stream()
          .map(Voucher::getVoucherId)
          .toList();

      // Batch fetch all voucher products
      Map<Long, List<VoucherProducto>> voucherProductosMap = voucherProductoService
           .findAllByVouchersIds(voucherIds)
          .stream()
          .collect(Collectors.groupingBy(VoucherProducto::getVoucherId));

      // Get all unique product IDs
      Set<Integer> allProductIds = voucherProductosMap.values().stream()
          .flatMap(List::stream)
          .map(vp -> vp.getProducto().getProductoId())
          .collect(Collectors.toSet());

      // Batch fetch all articles for all products
      Map<String, List<Articulo>> allArticulosByProducto = articuloService
          .findAllByProductoIds(new ArrayList<>(allProductIds))
          .stream()
          .collect(Collectors.groupingBy(Articulo::getArticuloId));

      // Get all grupos once
      List<Grupo> grupos = grupoService.findAllByVoucherFechaServicio(fechaServicio);
      
      // Batch fetch all productos by grupo
      Map<Integer, List<Producto>> productosByGrupo = grupos.stream()
          .collect(Collectors.toMap(
              Grupo::getGrupoId,
              grupo -> productoService.findAllByGrupoId(grupo.getGrupoId())
          ));

      // Create the final list
      return vouchers.stream()
          .map(voucher -> {
              List<VoucherProducto> voucherProductos = voucherProductosMap.getOrDefault(voucher.getVoucherId(), new ArrayList<>());
              
              Map<Integer, List<Articulo>> articulosByProducto = voucherProductos.stream()
                  .collect(Collectors.toMap(
                      vp -> vp.getProducto().getProductoId(),
                      vp -> allArticulosByProducto.getOrDefault(vp.getProducto().getProductoId(), new ArrayList<>())
                  ));

              List<VentasPorGrupoDto> ventasPorGrupo = grupos.stream()
                  .map(g -> {
                      List<Producto> productos = productosByGrupo.get(g.getGrupoId());
                      BigDecimal totalVentas = grupoService.totalVentasByGrupoAndVoucher(g.getGrupoId(), voucher.getVoucherId());

                      if (totalVentas == null || totalVentas.compareTo(BigDecimal.ZERO) == 0) {
                          return null;
                      }

                      Map<Integer, List<Articulo>> artsByProducto = productos.stream()
                          .collect(Collectors.toMap(
                              Producto::getProductoId,
                              producto -> allArticulosByProducto.getOrDefault(producto.getProductoId(), new ArrayList<>())
                          ));

                      return new VentasPorGrupoDto(fechaServicio, 
                          grupoToDtoMapper.toDto(g, productos, artsByProducto), 
                          totalVentas, 
                          null);
                  })
                  .filter(Objects::nonNull)
                  .toList();

              return new ProgramaDiaDetallesDto(
                  voucher.getFechaServicio(),
                  voucherToDtoMapper.toDto(voucher, voucherProductos, articulosByProducto),
                  reservaToDtoMapper.apply(voucher.getReserva()),
                  proveedorToDtoMapper.apply(voucher.getProveedor()),
                  ventasPorGrupo);
          })
          .toList();
   }
}
