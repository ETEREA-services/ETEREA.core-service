package eterea.core.service.controller;

import eterea.core.service.kotlin.exception.ArticuloListaPrecioException;
import eterea.core.service.kotlin.model.ArticuloListaPrecio;
import eterea.core.service.service.ArticuloListaPrecioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({"/api/core/articuloListaPrecio", "/articuloListaPrecio"})
public class ArticuloListaPrecioController {

    private final ArticuloListaPrecioService service;

    public ArticuloListaPrecioController(ArticuloListaPrecioService service) {
        this.service = service;
    }

    @GetMapping("/page")
    public ResponseEntity<CollectionModel<EntityModel<ArticuloListaPrecio>>> findAllPublicadosPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
            
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticuloListaPrecio> pageResult = service.findAllPublicadosPaginated(pageable);
        
        List<EntityModel<ArticuloListaPrecio>> articulos = pageResult.getContent().stream()
            .map(articulo -> EntityModel.of(articulo,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArticuloListaPrecioController.class)
                    .findByArticuloId(articulo.getArticuloId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArticuloListaPrecioController.class)
                    .findAllPublicadosPaginated(page, size)).withRel("articulos")))
            .collect(Collectors.toList());

        CollectionModel<EntityModel<ArticuloListaPrecio>> result = CollectionModel.of(articulos);
        
        if (pageResult.hasNext()) {
            result.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArticuloListaPrecioController.class)
                .findAllPublicadosPaginated(page + 1, size)).withRel("next"));
        }
        if (pageResult.hasPrevious()) {
            result.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArticuloListaPrecioController.class)
                .findAllPublicadosPaginated(page - 1, size)).withRel("prev"));
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{articuloId}")
    public ResponseEntity<ArticuloListaPrecio> findByArticuloId(@PathVariable String articuloId) {
        try {
            return ResponseEntity.ok(service.findByArticuloId(articuloId));
        } catch (ArticuloListaPrecioException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/publicar/{articuloId}/{publicar}")
    public ResponseEntity<ArticuloListaPrecio> publicar(@PathVariable String articuloId, @PathVariable Byte publicar) {
        return ResponseEntity.ok(service.publicar(articuloId, publicar));
    }

    @GetMapping("/rubro/{rubroId}/page")
    public ResponseEntity<CollectionModel<EntityModel<ArticuloListaPrecio>>> findAllByRubroIdPaginated(
            @PathVariable Integer rubroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
            
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticuloListaPrecio> pageResult = service.findAllByRubroIdPaginated(rubroId, pageable);
        
        List<EntityModel<ArticuloListaPrecio>> articulos = pageResult.getContent().stream()
            .map(articulo -> EntityModel.of(articulo,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArticuloListaPrecioController.class)
                    .findByArticuloId(articulo.getArticuloId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArticuloListaPrecioController.class)
                    .findAllByRubroIdPaginated(rubroId, page, size)).withRel("articulos"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RubroListaPrecioController.class)
                    .findAll()).withRel("rubros")))
            .collect(Collectors.toList());

        CollectionModel<EntityModel<ArticuloListaPrecio>> result = CollectionModel.of(articulos);
        
        if (pageResult.hasNext()) {
            result.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArticuloListaPrecioController.class)
                .findAllByRubroIdPaginated(rubroId, page + 1, size)).withRel("next"));
        }
        if (pageResult.hasPrevious()) {
            result.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArticuloListaPrecioController.class)
                .findAllByRubroIdPaginated(rubroId, page - 1, size)).withRel("prev"));
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/")
    public ResponseEntity<ArticuloListaPrecio> addOrUpdate(@RequestBody ArticuloListaPrecio articuloListaPrecio) {
        return ResponseEntity.ok(service.addOrUpdate(articuloListaPrecio));
    }

}
