package eterea.core.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.exception.PasswordValidationException;
import eterea.core.service.model.dto.LoginDto;
import eterea.core.service.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
   private final AuthService authService;

   public AuthController(AuthService authService) {
      this.authService = authService;
   }

   @PostMapping("/login")
   public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
      try {
         boolean isValid = authService.login(loginDto.username(), loginDto.password());
         if (isValid) {
            return ResponseEntity
                  .status(HttpStatus.OK)
                  .build();
         } else {
            return ResponseEntity
                  .status(HttpStatus.UNAUTHORIZED)
                  .build();
         }
      } catch (PasswordValidationException e) {
         return ResponseEntity
               .status(HttpStatus.UNPROCESSABLE_ENTITY)
               .build();
      }
   }
}