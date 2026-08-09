package eterea.core.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import eterea.core.service.exception.UsuarioException;
import eterea.core.service.repository.UsuarioRepository;
import eterea.core.service.kotlin.model.Usuario;

@Service
@RequiredArgsConstructor
public class AuthService {
   private final UsuarioRepository usuarioRepository;

   public boolean login(String username, String password) throws UsuarioException {
      Usuario usuario = usuarioRepository
         .findById(username)
         .orElseThrow(() -> new UsuarioException(username));
      String passwordHash = usuarioRepository.findPasswordHash(password);

      return usuario.getPassword().equals(passwordHash);
   }
}
