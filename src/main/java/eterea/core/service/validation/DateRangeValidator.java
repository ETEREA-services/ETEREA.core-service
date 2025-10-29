package eterea.core.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import eterea.core.service.model.dto.CreateHabitacionMovimientoDto;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, CreateHabitacionMovimientoDto> {

    @Override
    public boolean isValid(CreateHabitacionMovimientoDto dto, ConstraintValidatorContext context) {
        if (dto == null || dto.fechaIngreso() == null || dto.fechaSalida() == null) {
            return true; // @NotNull handles null validation
        }
        return dto.fechaIngreso().isBefore(dto.fechaSalida());
    }
}