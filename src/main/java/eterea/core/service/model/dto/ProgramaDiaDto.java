package eterea.core.service.model.dto;

import eterea.core.service.kotlin.model.ReservaOrigen;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.model.ClienteMovimiento;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaDiaDto {

    private List<Voucher> vouchers;
    private List<ReservaOrigen> reservaOrigens;
    private List<ClienteMovimiento> clienteMovimientos;
    @Builder.Default
    private String errorMessage = "";

}
