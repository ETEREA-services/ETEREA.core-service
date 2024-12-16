package eterea.core.service.client.core;

import eterea.core.service.kotlin.model.dto.ArticuloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ArticuloClient {

    @GetMapping("/{articuloId}")
    ArticuloDto findByArticuloId(@PathVariable String articuloId);

    @PostMapping("/")
    ArticuloDto add(@RequestBody ArticuloDto articulo);

}
