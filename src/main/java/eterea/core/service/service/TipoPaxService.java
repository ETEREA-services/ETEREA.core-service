package eterea.core.service.service;

import org.springframework.stereotype.Service;

import eterea.core.service.kotlin.exception.TipoPaxException;
import eterea.core.service.kotlin.model.TipoPax;
import eterea.core.service.kotlin.repository.TipoPaxRepository;

@Service
public class TipoPaxService {
   private final TipoPaxRepository repository;

   public TipoPaxService(TipoPaxRepository repository) {
      this.repository = repository;
   }

   public TipoPax findByNombre(String nombre) {
      return repository.findByNombre(nombre).orElseThrow(() -> new TipoPaxException(nombre));
   }
}
