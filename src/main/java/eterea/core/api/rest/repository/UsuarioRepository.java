package eterea.core.api.rest.repository;

import eterea.core.api.rest.kotlin.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByLogin(String login);

    void deleteByLogin(String login);

}
