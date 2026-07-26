package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model;

import eterea.core.service.hexagonal.negocio.domain.model.Negocio;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CierreCaja {

    private Long cierreCajaId;
    private Integer puntoVenta;
    private Integer negocioId;
    private OffsetDateTime fechaTope;
    private OffsetDateTime fechaCierre;
    private String usuario;
    private Integer nivel;
    private Integer negocioIdDesde;
    private Integer negocioIdHasta;
    private Integer transferenciaId;
    private Byte enviado;
    private Long id;

    private Negocio negocio;

}
