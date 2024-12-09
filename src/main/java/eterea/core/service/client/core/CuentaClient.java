package eterea.core.service.client.core;

import eterea.core.service.kotlin.model.dto.CuentaDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CuentaClient {

    @GetMapping("/{numeroCuenta}")
    CuentaDto findByNumeroCuenta(@PathVariable Long numeroCuenta);

}
