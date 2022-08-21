/**
 *
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 */
public class GrupoNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -722103449915789233L;

    public GrupoNotFoundException(Integer grupoId) {
        super("Cannot find Grupo " + grupoId);
    }

    public GrupoNotFoundException(String fecha) {
        super("Cannot find Grupo by date" + fecha);
    }
}
