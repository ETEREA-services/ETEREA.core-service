package eterea.core.service.client.core;

import eterea.core.service.model.dto.ParametroDto;
import org.springframework.web.bind.annotation.GetMapping;

public interface ParametroClient {

    @GetMapping("/top")
    ParametroDto findTop();

}
