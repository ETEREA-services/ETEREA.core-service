package eterea.core.service.model.dto.pluspagos;

import java.time.OffsetDateTime;
import java.util.Map;

public record ApiErrorDto(
      String code, // Código del error
      String message, // Mensaje del error
      String source, // Fuente del error (nombre del servicio que generó el error)
      String field, // Opcional: Errores de validación
      OffsetDateTime timestamp, // Fecha y hora del error
      Map<String, Object> details // Opcional: Detalles del error
) {

}
