package eterea.core.service.service;

import eterea.core.service.kotlin.model.HabitacionMovimiento;
import eterea.core.service.kotlin.repository.HabitacionMovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionMovimientoService {

    private final HabitacionMovimientoRepository repository;

    public HabitacionMovimientoService(HabitacionMovimientoRepository repository) {
        this.repository = repository;
    }

    public List<HabitacionMovimiento> findReservasSuperpuestas(
            Integer numeroHabitacion,
            OffsetDateTime fechaIngreso,
            OffsetDateTime fechaSalida,
            Long idExcluir) {
                
        // OffsetDateTime fechaIngresoMasUnDia = fechaIngreso.plusDays(1);
        // OffsetDateTime fechaSalidaMenosUnDia = fechaSalida.minusDays(1);
        // return repository.findReservasSuperpuestas(
        //     numeroHabitacion,
        //     fechaIngreso,
        //     fechaSalida,
        //     fechaIngresoMasUnDia,
        //     fechaSalidaMenosUnDia,
        //     idExcluir != null ? idExcluir : 0L
        // );
        // TODO: Revisar repository method porque da error 
        return null;
    }

    public HabitacionMovimiento save(HabitacionMovimiento habitacionMovimiento) {
        return repository.save(habitacionMovimiento);
    }

    public Optional<HabitacionMovimiento> findById(Long habitacionMovimientoId) {
        return repository.findById(habitacionMovimientoId);
    }
}
