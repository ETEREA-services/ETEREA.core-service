package eterea.core.service.hexagonal.transferencia.infrastructure.web.mapper;

import eterea.core.service.hexagonal.comprobante.application.service.ComprobanteService;
import eterea.core.service.hexagonal.comprobante.infrastructure.web.mapper.ComprobanteDtoMapper;
import eterea.core.service.hexagonal.transferencia.domain.model.Transferencia;
import eterea.core.service.hexagonal.transferencia.infrastructure.web.dto.TransferenciaResponse;
import eterea.core.service.model.dto.TransferenciaDto;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaDtoMapper {

    private final ComprobanteService comprobanteService;
    private final ComprobanteDtoMapper comprobanteDtoMapper;

    public TransferenciaDtoMapper(ComprobanteService comprobanteService, ComprobanteDtoMapper comprobanteDtoMapper) {
        this.comprobanteService = comprobanteService;
        this.comprobanteDtoMapper = comprobanteDtoMapper;
    }

    public TransferenciaResponse toResponse(Transferencia domain) {
        if (domain == null) {
            return null;
        }
        return TransferenciaResponse.builder()
                .transferenciaId(domain.getTransferenciaId())
                .negocioIdDesde(domain.getNegocioIdDesde())
                .negocioIdHasta(domain.getNegocioIdHasta())
                .numeroTransferencia(domain.getNumeroTransferencia())
                .fecha(domain.getFecha())
                .total(domain.getTotal())
                .ordenContable(domain.getOrdenContable())
                .transferido(domain.getTransferido())
                .comprobanteId(domain.getComprobanteId())
                .fechaOtro(domain.getFechaOtro())
                .build();
    }

    public TransferenciaDto toTransferenciaDto(Transferencia domain) {
        if (domain == null) {
            return null;
        }
        return TransferenciaDto.builder()
                .transferenciaId(domain.getTransferenciaId())
                .negocioIdDesde(domain.getNegocioIdDesde())
                .negocioIdHasta(domain.getNegocioIdHasta())
                .numeroTransferencia(domain.getNumeroTransferencia())
                .fecha(domain.getFecha())
                .total(domain.getTotal())
                .ordenContable(domain.getOrdenContable())
                .transferido(domain.getTransferido())
                .comprobanteId(domain.getComprobanteId())
                .fechaOtro(domain.getFechaOtro())
                .comprobante(comprobanteDtoMapper.toComprobanteDto(comprobanteService.findByComprobanteId(domain.getComprobanteId())))
                .build();
    }
}
