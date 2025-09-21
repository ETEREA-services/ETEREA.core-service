package eterea.core.service.controller;

import eterea.core.service.exception.UsuarioException;
import eterea.core.service.kotlin.model.Usuario;
import eterea.core.service.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping({"/api/core/usuario", "/usuario"})
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{login}")
    public ResponseEntity<Usuario> findByLogin(String login) {
        try {
            return new ResponseEntity<>(service.findByLogin(login), HttpStatus.OK);
        } catch (UsuarioException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> add(Usuario usuario) {
        return new ResponseEntity<>(service.add(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{login}")
    public ResponseEntity<Usuario> update(String login, Usuario usuario) {
        return new ResponseEntity<>(service.update(login, usuario), HttpStatus.OK);
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> delete(String login) {
        service.delete(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
