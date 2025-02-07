package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.model.dto.programadia.ProgramaDiaDetallesDto;
import eterea.core.service.model.dto.programadia.mapper.ProveedorToDtoMapper;
import eterea.core.service.model.dto.programadia.mapper.ReservaToDtoMapper;
import eterea.core.service.model.dto.programadia.mapper.VoucherToDtoMapper;

@Service
public class ProgramaDiaService {

   private final VoucherService voucherService;
   private final VoucherToDtoMapper voucherToDtoMapper;
   private final ReservaToDtoMapper reservaToDtoMapper;
   private final ProveedorToDtoMapper proveedorToDtoMapper;

   public ProgramaDiaService(VoucherService voucherService, VoucherToDtoMapper voucherToDtoMapper, ReservaToDtoMapper reservaToDtoMapper, ProveedorToDtoMapper proveedorToDtoMapper) {
      this.voucherService = voucherService;
      this.voucherToDtoMapper = voucherToDtoMapper;
      this.reservaToDtoMapper = reservaToDtoMapper;
      this.proveedorToDtoMapper = proveedorToDtoMapper;
   }

   public List<ProgramaDiaDetallesDto> getProgramaDiaDetalles(OffsetDateTime fechaServicio) {
      List<ProgramaDiaDetallesDto> programas = new ArrayList<>();
      List<Voucher> vouchers =  voucherService.findAllByFechaServicio(fechaServicio, false, false);

      vouchers.forEach(voucher -> {
         Reserva reserva = voucher.getReserva();
         Proveedor proveedor = voucher.getProveedor();

         programas.add(new ProgramaDiaDetallesDto(
            voucher.getFechaServicio(),
            voucherToDtoMapper.apply(voucher),
            reservaToDtoMapper.apply(reserva),
            proveedorToDtoMapper.apply(proveedor)
         ));
      });

      return programas;
   }
}
