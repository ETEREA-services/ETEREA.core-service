package eterea.core.api.rest.kotlin.exception

class RegistroCaeException(comprobanteId: Int, puntoVenta: Int, numeroComprobante: Long) : RuntimeException("Cannot fund RegistroCae $comprobanteId/$puntoVenta/$numeroComprobante")