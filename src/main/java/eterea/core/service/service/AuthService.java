package eterea.core.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import eterea.core.service.exception.PasswordValidationException;
import eterea.core.service.exception.UsuarioException;
import eterea.core.service.kotlin.model.Usuario;
import eterea.core.service.repository.UsuarioRepository;

@Service
public class AuthService {
   private final Logger log = LoggerFactory.getLogger(AuthService.class);
   private final UsuarioRepository usuarioRepository;

   public AuthService(UsuarioRepository usuarioRepository) {
      this.usuarioRepository = usuarioRepository;
   }

   public boolean login(String username, String password) throws UsuarioException {
      Usuario usuario = usuarioRepository
            .findById(username)
            .orElseThrow(() -> new UsuarioException(username));
      String passwordHash = usuarioRepository.findPasswordHash(password);
      boolean isLoggedIn = usuario.getPassword().equals(passwordHash);

      // if (isLoggedIn) {
      //    validatePassword(username, password);
      //    return isLoggedIn;
      // }

      return isLoggedIn;
   }

   /**
    * Validates password according to security requirements:
    * - At least 6 characters
    * - Contains at least one uppercase letter
    * - Contains at least one lowercase letter
    * - Contains at least one number
    */
   private void validatePassword(String username, String password) throws PasswordValidationException {
      if (username.equals("admin")) {
         return;
      }

      if (password == null || password.length() < 6) {
         throw new PasswordValidationException("Password must be at least 6 characters long");
      }

      boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
      boolean hasLowerCase = password.chars().anyMatch(Character::isLowerCase);
      boolean hasDigit = password.chars().anyMatch(Character::isDigit);

      if (!hasUpperCase) {
         throw new PasswordValidationException("Password must contain at least one uppercase letter");
      }

      if (!hasLowerCase) {
         throw new PasswordValidationException("Password must contain at least one lowercase letter");
      }

      if (!hasDigit) {
         throw new PasswordValidationException("Password must contain at least one number");
      }
   }
}
