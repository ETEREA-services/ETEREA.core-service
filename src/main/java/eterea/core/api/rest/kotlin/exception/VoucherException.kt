package eterea.core.api.rest.kotlin.exception

class VoucherException : RuntimeException {

    constructor(valueId: Long, string: String) : super("Cannot find Voucher $string -> $valueId")

    constructor(numeroVoucher: String) : super("Cannot find Voucher by numero_voucher $numeroVoucher")

}