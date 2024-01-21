package eterea.core.api.rest.service.facade;

import eterea.core.api.rest.kotlin.model.Cliente;
import eterea.core.api.rest.kotlin.model.Comprobante;
import eterea.core.api.rest.kotlin.model.Empresa;
import eterea.core.api.rest.kotlin.model.Reserva;
import eterea.core.api.rest.service.ComprobanteService;
import eterea.core.api.rest.service.EmpresaService;
import eterea.core.api.rest.service.ReservaService;
import eterea.core.api.rest.tool.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeFacturaService {

    private final ComprobanteService comprobanteService;

    private final ReservaService reservaService;

    private final EmpresaService empresaService;

    @Autowired
    public MakeFacturaService(ComprobanteService comprobanteService, ReservaService reservaService, EmpresaService empresaService) {
        this.comprobanteService = comprobanteService;
        this.reservaService = reservaService;
        this.empresaService = empresaService;
    }

    public void facturaReserva(Long reservaId, Integer comprobanteId) {
        Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
        if (comprobante.getFacturaElectronica() == 0) {
            return;
        }
        Empresa empresa = empresaService.findTop();
        String moneda = "PES";
        Reserva reserva = reservaService.findByReservaId(reservaId);
        Cliente cliente = reserva.getCliente();

        int tipoDocumento = 80;
        String documento = cliente.getCuit().replace("-", "").trim();
        if (cliente.getTipoDocumento().trim().toUpperCase().substring(0, 3).equals("PAS")) {
            tipoDocumento = 94;
            documento = ToolService.onlyNumbers(cliente.getNumeroDocumento());
        } else {
            if (Integer.parseInt(documento) == 0) {
                tipoDocumento = 96;
                documento = ToolService.onlyNumbers(cliente.getNumeroDocumento());
            }

            if (Integer.parseInt(documento) == 0) {
                tipoDocumento = 99;
                documento = "0";
            }
        }
    }

}
