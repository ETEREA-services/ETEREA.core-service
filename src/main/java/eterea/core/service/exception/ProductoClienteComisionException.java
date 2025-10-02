package eterea.core.service.exception;

public class ProductoClienteComisionException extends RuntimeException {

   public ProductoClienteComisionException(Long productoId, Long clienteId) {
      super("Cannot find ProductoClienteComision with productoId: " + productoId + " and clienteId: " + clienteId);
   }

   public ProductoClienteComisionException(Long clienteId, Long reservaId, String articuloId) {
      super("Cannot find ProductoClienteComision with clienteId: " + clienteId + " and reservaId: " + reservaId + " and articuloId: " + articuloId);
   }

}
