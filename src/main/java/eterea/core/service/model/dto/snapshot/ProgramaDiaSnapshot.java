package eterea.core.service.model.dto.snapshot;

import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaDiaSnapshot {

    private String observaciones;
    private Voucher voucher;
    private OrderNote orderNote;
    private Reserva reserva;
    private Cliente cliente;
    private Comprobante comprobante;
    private FacturacionDto facturacionDto;
    private Valor valor;
    private ReservaContext reservaContext;
    private ClienteMovimiento clienteMovimiento;
    private ValorMovimiento valorMovimiento;
    private List<ReservaArticulo> reservaArticulos;
    private List<ArticuloMovimiento> articuloMovimientos;
    private List<CuentaMovimiento> cuentaMovimientos;

}
