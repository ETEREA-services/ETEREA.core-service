package eterea.core.api.rest.exception;

public class VoucherProductoException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 7183478533752360467L;

    public VoucherProductoException(Long voucherId, Long productoId) {
        super("Cannot find VoucherProducto " + voucherId + "/" + productoId);
    }
    public VoucherProductoException(Long voucherId) {
        super("Cannot find VoucherProducto " + voucherId);
    }
}
