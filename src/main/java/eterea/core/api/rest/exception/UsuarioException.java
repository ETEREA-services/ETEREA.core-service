package eterea.core.api.rest.exception;

public class UsuarioException  extends RuntimeException {

    public UsuarioException(String login) {
        super("Cannot find user with login " + login);
    }

}
