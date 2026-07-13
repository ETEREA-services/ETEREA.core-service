package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.application.exception;

public class CierreCajaAnticipoHaberesException extends RuntimeException {

    public CierreCajaAnticipoHaberesException() {
        super("Cannot find CierreCajaAnticipoHaberes");
    }

    public CierreCajaAnticipoHaberesException(Long cierreCajaId) {
        super("Cannot find CierreCajaAnticipoHaberes for cierreCajaId: " + cierreCajaId);
    }

}
