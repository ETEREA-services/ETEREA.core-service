package eterea.core.service.service;

import org.springframework.stereotype.Service;

import eterea.core.service.kotlin.model.TipoDia;
import eterea.core.service.kotlin.repository.TipoDiaRepository;
import eterea.core.service.kotlin.exception.TipoDiaException;

@Service
public class TipoDiaService {
   private final TipoDiaRepository repository;

   public TipoDiaService(TipoDiaRepository repository) {
      this.repository = repository;
   }

   public TipoDia findByNombre(String nombre) {
      return repository.findByNombre(nombre).orElseThrow(() -> new TipoDiaException(nombre));
   }
}
