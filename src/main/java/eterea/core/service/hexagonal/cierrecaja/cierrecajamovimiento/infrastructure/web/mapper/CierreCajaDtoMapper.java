package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.web.mapper;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.web.dto.CierreCajaResponse;
import eterea.core.service.hexagonal.negocio.infrastructure.web.mapper.NegocioDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CierreCajaDtoMapper {

    private final NegocioDtoMapper negocioDtoMapper;

    public CierreCajaResponse toResponse(CierreCaja domain) {
        if (domain == null) {
            return null;
        }
        return CierreCajaResponse.builder()
                .cierreCajaId(domain.getCierreCajaId())
                .puntoVenta(domain.getPuntoVenta())
                .negocioId(domain.getNegocioId())
                .fechaTope(domain.getFechaTope())
                .fechaCierre(domain.getFechaCierre())
                .usuario(domain.getUsuario())
                .nivel(domain.getNivel())
                .negocioIdDesde(domain.getNegocioIdDesde())
                .negocioIdHasta(domain.getNegocioIdHasta())
                .transferenciaId(domain.getTransferenciaId())
                .enviado(domain.getEnviado())
                .id(domain.getId())
                .negocio(negocioDtoMapper.toResponse(domain.getNegocio()))
                .build();
    }

}
