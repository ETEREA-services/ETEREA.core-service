/**
 *
 */
package eterea.core.service.service.facade;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eterea.core.service.exception.ArticuloException;
import eterea.core.service.exception.ArticuloFechaException;
import eterea.core.service.kotlin.exception.FeriadoException;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.ArticuloFecha;
import eterea.core.service.kotlin.model.ProductoArticulo;
import eterea.core.service.kotlin.model.dto.PriceDto;
import eterea.core.service.service.FeriadoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import eterea.core.service.service.ArticuloFechaService;
import eterea.core.service.service.ArticuloService;
import eterea.core.service.service.ProductoArticuloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class PrecioService {

    private final ArticuloFechaService articuloFechaService;
    private final ArticuloService articuloService;
    private final ProductoArticuloService productoArticuloService;
    private final FeriadoService feriadoService;

    public PrecioService(ArticuloFechaService articuloFechaService,
                         ArticuloService articuloService,
                         ProductoArticuloService productoArticuloService,
                         FeriadoService feriadoService) {
        this.articuloFechaService = articuloFechaService;
        this.articuloService = articuloService;
        this.productoArticuloService = productoArticuloService;
        this.feriadoService = feriadoService;
    }

    public BigDecimal getUnitPriceByArticuloIdAndFecha(String articuloId, OffsetDateTime fecha) {
        BigDecimal preciounitario = new BigDecimal(0);
        ArticuloFecha articulofecha = null;
        try {
            articulofecha = articuloFechaService.findByUnique(articuloId, fecha);
        } catch (ArticuloFechaException e) {
            log.debug(e.getMessage());
        }
        if (articulofecha == null) {
            Articulo articulo = null;
            try {
                articulo = articuloService.findByArticuloId(articuloId);
            } catch (ArticuloException e) {
                log.debug(e.getMessage());
            }
            if (articulo != null)
                preciounitario = articulo.getPrecioVentaConIva();
        } else
            preciounitario = articulofecha.getPrecioArs();
        return preciounitario;
    }

    public BigDecimal getUnitPriceByProductoIdAndFecha(Integer productoId, OffsetDateTime fecha) {
        BigDecimal preciounitario = new BigDecimal(0);
        for (ProductoArticulo productoarticulo : productoArticuloService.findAllByProductoId(productoId)) {
            preciounitario = preciounitario
                    .add(getUnitPriceByArticuloIdAndFecha(productoarticulo.getArticuloId(), fecha));
        }
        return preciounitario;
    }

    @Transactional
    public Boolean setUnitPriceByPeriod(PriceDto priceDto) {
        List<ArticuloFecha> toSave = new ArrayList<>();
        var articulofechas = articuloFechaService.findAllByArticuloIdAndPeriodo(
                        priceDto.getArticuloId(), priceDto.getFechaInicio(), priceDto.getFechaFin()).stream()
                .collect(Collectors.toMap(ArticuloFecha::getFecha, articulofecha -> articulofecha));
        for (OffsetDateTime fecha = priceDto.getFechaInicio(); fecha.isBefore(priceDto.getFechaFin().plusDays(1)); fecha = fecha.plusDays(1)) {
            Long articuloFechaId = null;
            if (articulofechas.containsKey(fecha)) {
                articuloFechaId = articulofechas.get(fecha).getArticuloFechaId();
            }
            // Inicializar el precio en 0
            BigDecimal precio = BigDecimal.ZERO;

            // Obtener el día de la semana
            DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();

            // Calcular el precio según el día de la semana
            switch (diaDeLaSemana) {
                case MONDAY:
                    precio = priceDto.getPrecioLunes();
                    break;
                case TUESDAY:
                    precio = priceDto.getPrecioMartes();
                    break;
                case WEDNESDAY:
                    precio = priceDto.getPrecioMiercoles();
                    break;
                case THURSDAY:
                    precio = priceDto.getPrecioJueves();
                    break;
                case FRIDAY:
                    precio = priceDto.getPrecioViernes();
                    break;
                case SATURDAY:
                    precio = priceDto.getPrecioSabado();
                    break;
                case SUNDAY:
                    precio = priceDto.getPrecioDomingo();
                    break;
            }

            // Verificar si es feriado y, si lo es, asignar el precio de feriado
            try {
                feriadoService.findByFecha(fecha);
                precio = priceDto.getPrecioFeriado();
            } catch (FeriadoException e) {
                log.debug("No es Feriado");
            }

            toSave.add(new ArticuloFecha.Builder()
                    .articuloFechaId(articuloFechaId)
                    .articuloId(priceDto.getArticuloId())
                    .fecha(fecha)
                    .precioArs(precio)
                    .build());

        }
        try {
            articuloFechaService.saveAll(toSave);
        } catch (ArticuloFechaException e) {
            log.debug("Error -> {}", e.getMessage());
            return false;
        }
        return true;
    }
}
