package eterea.core.service.service;

import org.springframework.stereotype.Service;

import eterea.core.service.kotlin.model.ReservaArticuloEliminado;
import eterea.core.service.kotlin.repository.ReservaArticuloEliminadoRepository;

@Service
public class ReservaArticuloEliminadoService {

   private final ReservaArticuloEliminadoRepository repository;

   public ReservaArticuloEliminadoService(ReservaArticuloEliminadoRepository repository) {
      this.repository = repository;
   }

   public ReservaArticuloEliminado create(ReservaArticuloEliminado reservaArticuloEliminado) {
      return repository.save(reservaArticuloEliminado);
   }

}
