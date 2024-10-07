package eterea.core.service.service;

import eterea.core.service.exception.InventarioTurnoException;
import eterea.core.service.kotlin.model.InventarioTurno;
import eterea.core.service.repository.InventarioTurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioTurnoService {

    private final InventarioTurnoRepository repository;

    public InventarioTurnoService(InventarioTurnoRepository repository) {
        this.repository = repository;
    }

    public List<InventarioTurno> findAll() {
        return repository.findAll();
    }

    public InventarioTurno findByInventarioTurnoId(Integer inventarioTurnoId) {
        return repository.findByInventarioTurnoId(inventarioTurnoId).orElseThrow(() -> new InventarioTurnoException(inventarioTurnoId));
    }

    public InventarioTurno findLast() {
        return repository.findFirstByOrderByInventarioTurnoIdDesc().orElseThrow(() -> new InventarioTurnoException(null));
    }

    public InventarioTurno create(InventarioTurno inventarioTurno) {
        return repository.save(inventarioTurno);
    }

    public InventarioTurno update(Integer inventarioTurnoId, InventarioTurno newInventarioTurno) {
        return repository.findByInventarioTurnoId(inventarioTurnoId).map(inventarioTurno -> {
            inventarioTurno.setNombre(newInventarioTurno.getNombre());
            return repository.save(inventarioTurno);
        }).orElseThrow(() -> new InventarioTurnoException(inventarioTurnoId));
    }
}
