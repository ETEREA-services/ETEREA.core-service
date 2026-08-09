package eterea.core.service.hexagonal.reservas.voucher.application.exception;

public class VoucherException extends RuntimeException {

    public VoucherException(Long valueId, String string) {
        super("Cannot find Voucher " + string + " -> " + valueId);
    }

    public VoucherException(String numeroVoucher) {
        super("Cannot find Voucher by numero_voucher " + numeroVoucher);
    }

}
