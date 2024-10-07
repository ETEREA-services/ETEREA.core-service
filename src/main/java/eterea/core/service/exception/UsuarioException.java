package eterea.core.service.exception;

public class UsuarioException  extends RuntimeException {

    public UsuarioException(String login) {
        super("Cannot find user with login " + login);
    }

}
