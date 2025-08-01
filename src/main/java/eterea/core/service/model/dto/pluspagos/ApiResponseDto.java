package eterea.core.service.model.dto.pluspagos;

import java.util.List;

public record ApiResponseDto<T>(
      T data,
      List<ApiErrorDto> warnings,
      List<ApiErrorDto> errors,
      String status) {

}
