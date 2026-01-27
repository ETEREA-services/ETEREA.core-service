package eterea.core.service.hexagonal.legajo.domain.ports.out;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface LegajoRepository {

    List<Legajo> findAll();
    Optional<Legajo> findByLegajoId(Integer legajoId);
    Legajo createLegajo(Legajo legajo);
    List<Legajo> findAllContainingSubstrings(List<String> substrings);

}
