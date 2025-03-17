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
                
        return repository.findReservasSuperpuestas(
            numeroHabitacion,
            fechaIngreso,
            fechaSalida,
            idExcluir != null ? idExcluir : 0L
        );
    }

    public HabitacionMovimiento save(HabitacionMovimiento habitacionMovimiento) {
        return repository.save(habitacionMovimiento);
    }

    public Optional<HabitacionMovimiento> findById(Long habitacionMovimientoId) {
        return repository.findById(habitacionMovimientoId);
    }
}
