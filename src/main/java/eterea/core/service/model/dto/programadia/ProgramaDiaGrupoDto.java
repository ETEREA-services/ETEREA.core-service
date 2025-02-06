package eterea.core.service.model.dto.programadia;

import java.util.List;

public record ProgramaDiaGrupoDto(Integer grupoId, String grupoNombre, List<ProgramaDiaProductoDto> productos) {

}
