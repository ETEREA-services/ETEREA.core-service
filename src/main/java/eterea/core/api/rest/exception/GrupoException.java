/**
 *
 */
package eterea.core.api.rest.exception;

/**
 * @author daniel
 */
public class GrupoException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -722103449915789233L;

    public GrupoException(Integer grupoId) {
        super("Cannot find Grupo " + grupoId);
    }

    public GrupoException(String fecha) {
        super("Cannot find Grupo by date" + fecha);
    }
}
