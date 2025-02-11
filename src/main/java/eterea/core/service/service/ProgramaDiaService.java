package eterea.core.service.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
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
import eterea.core.service.kotlin.model.ProductoArticulo;




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
   private final ProductoArticuloService productoArticuloService;


   public ProgramaDiaService(VoucherService voucherService, VoucherToDtoMapper voucherToDtoMapper,
         ReservaToDtoMapper reservaToDtoMapper, ProveedorToDtoMapper proveedorToDtoMapper,
         ProductoService productoService, ArticuloService articuloService,
         ProductoToDtoMapper productoToDtoMapper, VoucherProductoService voucherProductoService,
         GrupoService grupoService, GrupoToDtoMapper grupoToDtoMapper,
         ProductoArticuloService productoArticuloService) {


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
      this.productoArticuloService = productoArticuloService;
   }



   // public List<ProgramaDiaDetallesDto> getProgramaDiaDetalles(OffsetDateTime fechaServicio) {
   //    List<ProgramaDiaDetallesDto> programas = new ArrayList<>();
   //    List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, false, false);

   //    vouchers.forEach(voucher -> {
   //       Reserva reserva = voucher.getReserva();
   //       Proveedor proveedor = voucher.getProveedor();
   //       List<VoucherProducto> voucherProductos = voucherProductoService.findAllByVoucherId(voucher.getVoucherId());
   //       Map<Integer, List<Articulo>> articulosByProducto = voucherProductos.stream()
   //             .collect(Collectors.toMap(
   //                   voucherProducto -> voucherProducto.getProducto().getProductoId(),
   //                   voucherProducto -> articuloService.findAllByProductoId(voucherProducto.getProducto().getProductoId())));
   //       // List<Producto> productos = productoService.findAllByVoucherId(voucher.getVoucherId());
   //       // Map<Integer, List<Articulo>> articulosByProducto = productos.stream()
   //       //       .collect(Collectors.toMap(
   //       //             Producto::getProductoId,
   //       //             producto -> articuloService.findAllByProductoId(producto.getProductoId())));
   //       List<Grupo> grupos = grupoService.findAllByVoucherFechaServicio(fechaServicio);
   //       List<VentasPorGrupoDto> ventasPorGrupo = grupos.stream()
   //             .map(g -> {
   //                List<Producto> productos = productoService.findAllByGrupoId(g.getGrupoId());
   //                Map<Integer, List<Articulo>> artsByProducto = productos.stream()
   //                      .collect(Collectors.toMap(
   //                            Producto::getProductoId,
   //                            producto -> articuloService.findAllByProductoId(producto.getProductoId())));
   //                BigDecimal totalVentas = grupoService.totalVentasByGrupoAndVoucher(g.getGrupoId(), voucher.getVoucherId());

   //                if (totalVentas == null || totalVentas.compareTo(BigDecimal.ZERO) == 0) {
   //                   return null;
   //                }
   //                return new VentasPorGrupoDto(fechaServicio, grupoToDtoMapper.toDto(g, productos, articulosByProducto), totalVentas, null);
   //             })
   //             .filter(Objects::nonNull)
   //             .toList();

   //       programas.add(new ProgramaDiaDetallesDto(
   //             voucher.getFechaServicio(),
   //             voucherToDtoMapper.toDto(voucher, voucherProductos, articulosByProducto),
   //             reservaToDtoMapper.apply(reserva),
   //             proveedorToDtoMapper.apply(proveedor),
   //             ventasPorGrupo));
   //    });
   //    return programas;
   // }
   public List<ProgramaDiaDetallesDto> getProgramaDiaDetalles(OffsetDateTime fechaServicio) {
    List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, false, false);
    if (vouchers.isEmpty()) {
        return new ArrayList<>();
    }

    List<Long> voucherIds = vouchers.stream()
        .map(Voucher::getVoucherId)
        .toList();

    // Fetch all voucherproductos
    Map<Long, List<VoucherProducto>> voucherProductosMap = voucherProductoService
        .findAllByVoucherIds(voucherIds)
        .stream()
        .collect(Collectors.groupingBy(VoucherProducto::getVoucherId));
    
    // Get all voucher products
//    List<Producto> productos = voucherProductosMap.values().stream()
//        .flatMap(List::stream)
//        .map(vp -> vp.getProducto())
//        .toList();

    // Get all unique product IDs
    Set<Integer> allProductIds = voucherProductosMap.values().stream()
        .flatMap(List::stream)
        .map(vp -> vp.getProducto().getProductoId())
        .collect(Collectors.toSet());

    // Batch fetch all ProductoArticulo relationships and their articles
    List<ProductoArticulo> productoArticulos = productoArticuloService
        .findAllByProductoIdIn(new ArrayList<>(allProductIds));

    // Rename this main map that contains all articulos
    Map<Integer, List<Articulo>> globalArticulosByProducto = productoArticulos.stream()
        .filter(pa -> pa.getArticulo() != null)
        .collect(Collectors.groupingBy(
            pa -> pa.getProductoId(),
            Collectors.mapping(pa -> pa.getArticulo(), Collectors.toList())
        ));

    // Get all grupos once
    List<Grupo> grupos = grupoService.findAllByVoucherFechaServicio(fechaServicio);
    
    // Fetch all productos by grupo
    Map<Integer, List<Producto>> productosByGrupo = grupos.stream()
        .collect(Collectors.toMap(
            Grupo::getGrupoId,
            grupo -> productoService.findAllByGrupoId(grupo.getGrupoId())
        ));
    
    

    // Create the final list
    return vouchers.stream()
        .map(voucher -> {
            List<VoucherProducto> voucherProductos = voucherProductosMap.getOrDefault(voucher.getVoucherId(), new ArrayList<>());
            
            Map<Integer, List<Articulo>> voucherArticulosByProducto = voucherProductos.stream()
                .collect(Collectors.toMap(
                    vp -> vp.getProducto().getProductoId(),
                    vp -> globalArticulosByProducto.getOrDefault(vp.getProducto().getProductoId(), new ArrayList<>())
                ));

            List<VentasPorGrupoDto> ventasPorGrupo = grupos.stream()
                .map(g -> {
                    List<Producto> productos = productosByGrupo.get(g.getGrupoId());
                    BigDecimal totalVentas = grupoService.totalVentasByGrupoAndVoucher(g.getGrupoId(), voucher.getVoucherId());

                    if (totalVentas == null || totalVentas.compareTo(BigDecimal.ZERO) == 0) {
                        return null;
                    }

                    Map<Integer, List<Articulo>> grupoArticulosByProducto = productos.stream()
                        .collect(Collectors.toMap(
                            Producto::getProductoId,
                            producto -> globalArticulosByProducto.getOrDefault(producto.getProductoId(), new ArrayList<>())
                        ));

                    return new VentasPorGrupoDto(fechaServicio, 
                        grupoToDtoMapper.toDto(g, productos, grupoArticulosByProducto), 
                        totalVentas, 
                        null);
                })
                .filter(Objects::nonNull)
                .toList();

            return new ProgramaDiaDetallesDto(
                voucher.getFechaServicio(),
                voucherToDtoMapper.toDto(voucher, voucherProductos, voucherArticulosByProducto),
                reservaToDtoMapper.apply(voucher.getReserva()),
                proveedorToDtoMapper.apply(voucher.getProveedor()),
                ventasPorGrupo);
        })
        .toList();
   }
}
