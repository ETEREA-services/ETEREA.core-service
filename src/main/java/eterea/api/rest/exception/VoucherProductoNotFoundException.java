package eterea.api.rest.exception;

public class VoucherProductoNotFoundException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 7183478533752360467L;

    public VoucherProductoNotFoundException(Long voucherId, Long productoId) {
        super("Cannot find VoucherProducto " + voucherId + "/" + productoId);
    }
    public VoucherProductoNotFoundException(Long voucherId) {
        super("Cannot find VoucherProducto " + voucherId);
    }
}
