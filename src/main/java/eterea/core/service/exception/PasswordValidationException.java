package eterea.core.service.exception;

public class PasswordValidationException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public PasswordValidationException(String message) {
        super(message);
    }
} 