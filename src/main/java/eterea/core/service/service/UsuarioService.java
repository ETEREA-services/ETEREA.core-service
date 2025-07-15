package eterea.core.service.service;

import eterea.core.service.exception.UsuarioException;
import eterea.core.service.kotlin.model.Usuario;
import eterea.core.service.repository.UsuarioRepository;
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
        Usuario usuario = repository.findByLogin(login)
                .orElseThrow(() -> new UsuarioException(login));

        usuario.setDescripcion(newUsuario.getDescripcion());
        usuario.setPassword(newUsuario.getPassword());
        usuario.setEmail(newUsuario.getEmail());
        usuario.setNivel(newUsuario.getNivel());
        usuario.setPin(newUsuario.getPin());
        usuario.setCierreRecipientType(newUsuario.getCierreRecipientType());
        usuario.setConsolidadoRecipientType(newUsuario.getConsolidadoRecipientType());
        usuario.setUsuarioId(newUsuario.getUsuarioId());

        return repository.save(usuario);
    }

    public void delete(String login) {
        repository.deleteByLogin(login);
    }
}
