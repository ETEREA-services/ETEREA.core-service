package eterea.core.service.model.dto;

import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.VoucherProducto;

import java.util.List;

import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.ReservaArticulo;

public record PregenerarReservaDto(Voucher voucher, List<VoucherProducto> voucherProductos, Reserva reserva,
      List<ReservaArticulo> reservaArticulos) {

}
