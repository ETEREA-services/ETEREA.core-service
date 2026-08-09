package eterea.core.service.hexagonal.ventas.productoarticulo.application.exception;

public class ProductoArticuloException extends RuntimeException {

    public ProductoArticuloException() {
        super("Cannot find ProductoArticulo");
    }

    public ProductoArticuloException(Integer productoId, String articuloId) {
        super("Cannot find ProductoArticulo " + productoId + "/" + articuloId);
    }

}
