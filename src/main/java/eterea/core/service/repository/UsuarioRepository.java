package eterea.core.service.repository;

import eterea.core.service.kotlin.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByLogin(String login);

    void deleteByLogin(String login);

    @Query(value = """
            SELECT PASSWORD(?1)
            """, nativeQuery = true)
    String findPasswordHash(String password);

}
