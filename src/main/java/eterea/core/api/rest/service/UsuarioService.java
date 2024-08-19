package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.UsuarioException;
import eterea.core.api.rest.kotlin.model.Usuario;
import eterea.core.api.rest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findByLogin(String login) {
        return repository.findByLogin(login).orElseThrow(() -> new UsuarioException(login));
    }

    public Usuario add(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario update(String login, Usuario newUsuario) {
        return repository.findByLogin(login)
                .map(usuario -> {
                    usuario = new Usuario.Builder()
                            .login(login)
                            .descripcion(newUsuario.getDescripcion())
                            .password(newUsuario.getPassword())
                            .email(newUsuario.getEmail())
                            .nivel(newUsuario.getNivel())
                            .pin(newUsuario.getPin())
                            .cierreRecipientType(newUsuario.getCierreRecipientType())
                            .consolidadoRecipientType(newUsuario.getConsolidadoRecipientType())
                            .usuarioId(newUsuario.getUsuarioId())
                            .build();
                    return repository.save(usuario);
                }).orElseThrow(() -> new UsuarioException(login));
    }

    public void delete(String login) {
        repository.deleteByLogin(login);
    }
}
