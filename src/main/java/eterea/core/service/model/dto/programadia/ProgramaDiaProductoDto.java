package eterea.core.service.model.dto.programadia;

import java.util.List;

public record ProgramaDiaProductoDto(Integer productoId, String productoNombre, String productoObs,
      List<ProgramaDiaArticuloDto> articulos) {

}
