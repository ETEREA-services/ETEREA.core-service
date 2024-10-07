/**
 *
 */
package eterea.core.service.controller.facade;

import java.time.OffsetDateTime;

import eterea.core.service.kotlin.model.dto.ProgramaDiaDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.core.service.exception.ProgramaDiaException;
import eterea.core.service.service.facade.ProgramaDiaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/programaDia", "/programaDia"})
public class ProgramaDiaController {

    private final ProgramaDiaService service;

    public ProgramaDiaController(ProgramaDiaService service) {
        this.service = service;
    }

    @GetMapping("/fechaServicio/{fechaServicio}/{soloConfirmados}/{porNombrePax}")
    public ResponseEntity<ProgramaDiaDto> findAllByFecha(
            @PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fechaServicio,
            @PathVariable Boolean soloConfirmados, @PathVariable Boolean porNombrePax) {
        return new ResponseEntity<>(
                service.findAllByFechaServicio(fechaServicio, soloConfirmados, porNombrePax), HttpStatus.OK);
    }

    @GetMapping("/voucher/{voucherId}")
    public ResponseEntity<ProgramaDiaDto> findByVoucherId(@PathVariable Long voucherId) {
        try {
            return new ResponseEntity<>(service.findByVoucherId(voucherId), HttpStatus.OK);
        } catch (ProgramaDiaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/importOneFromWeb/{orderNumberId}")
    public ResponseEntity<ProgramaDiaDto> importOneFromWeb(@PathVariable Long orderNumberId) {
        try {
            return new ResponseEntity<>(service.importOneFromWeb(orderNumberId), HttpStatus.OK);
        } catch (ProgramaDiaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Scheduled(cron = "0 30 * * * *")
    @GetMapping("/importManyCompletedFromWeb")
    public ResponseEntity<Void> importManyCompletedFromWeb() {
        service.importManyCompletedFromWeb();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
