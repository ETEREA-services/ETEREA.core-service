package eterea.core.service.model.dto;

import java.util.List;

public record PageDto<T>(
    List<T> content,
    int number,
    int size,
    int numberOfElements,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last,
    boolean empty
) {
   
}
