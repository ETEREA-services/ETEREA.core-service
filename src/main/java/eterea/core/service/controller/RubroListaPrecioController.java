package eterea.core.service.controller;

import eterea.core.service.kotlin.exception.RubroListaPrecioException;
import eterea.core.service.kotlin.model.RubroListaPrecio;
import eterea.core.service.service.RubroListaPrecioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping({"/api/core/rubroListaPrecio", "/rubroListaPrecio"})
public class RubroListaPrecioController {

    private final RubroListaPrecioService service;

    public RubroListaPrecioController(RubroListaPrecioService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<RubroListaPrecio>>> findAll() {
        List<EntityModel<RubroListaPrecio>> rubros = service.findAllByPublicar().stream()
            .map(rubro -> EntityModel.of(rubro,
                linkTo(methodOn(RubroListaPrecioController.class).findAll()).withRel("rubros"),
                linkTo(methodOn(ArticuloListaPrecioController.class)
                    .findAllPublicadosPaginated(0, 30)).withRel("articulos")))
            .collect(Collectors.toList());

        return ResponseEntity.ok(
            CollectionModel.of(rubros,
                linkTo(methodOn(RubroListaPrecioController.class).findAll()).withSelfRel())
        );
    }

    @GetMapping("/{rubroId}")
    public ResponseEntity<RubroListaPrecio> findByRubroId(@PathVariable Long rubroId) {
        try {
            return ResponseEntity.ok(service.findByRubroId(rubroId));
        } catch (RubroListaPrecioException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<RubroListaPrecio> addOrUpdate(@RequestBody RubroListaPrecio rubroListaPrecio) {
        return ResponseEntity.ok(service.addOrUpdate(rubroListaPrecio));
    }

}
