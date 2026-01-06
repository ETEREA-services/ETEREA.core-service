/**
 *
 */
package eterea.core.service.hexagonal.negocio.infrastructure.web.controller;

import eterea.core.service.hexagonal.negocio.domain.ports.in.GetAllNegociosWithIpAddressUseCase;
import eterea.core.service.hexagonal.negocio.domain.ports.in.GetNegocioByIdUseCase;
import eterea.core.service.hexagonal.negocio.infrastructure.web.dto.NegocioResponse;
import eterea.core.service.hexagonal.negocio.infrastructure.web.mapper.NegocioDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/negocio", "/negocio"})
@RequiredArgsConstructor
public class NegocioController {

    private final NegocioDtoMapper negocioDtoMapper;
    private final GetNegocioByIdUseCase getNegocioByIdUseCase;
    private final GetAllNegociosWithIpAddressUseCase getAllNegociosWithIpAddressUseCase;

    @GetMapping("/with/ip/{negocioId}")
    public ResponseEntity<List<NegocioResponse>> getAllNegociosWithIpAddress(@PathVariable Integer negocioId) {
        return ResponseEntity.ok(getAllNegociosWithIpAddressUseCase.getAllNegociosWithIpAddress(negocioId)
                .stream()
                .map(negocioDtoMapper::toResponse)
                .toList());
    }

    @GetMapping("/{negocioId}")
    public ResponseEntity<NegocioResponse> getNegocioById(@PathVariable Integer negocioId) {
        return getNegocioByIdUseCase.getNegocioById(negocioId)
                .map(negocio -> ResponseEntity.ok(negocioDtoMapper.toResponse(negocio)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

}
