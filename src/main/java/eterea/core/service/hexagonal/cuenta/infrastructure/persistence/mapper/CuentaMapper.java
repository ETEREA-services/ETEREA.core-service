package eterea.core.service.hexagonal.cuenta.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.cuenta.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {

    public Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) {
            return null;
        }
        return Cuenta.builder()
                .numeroCuenta(entity.getNumeroCuenta())
                .nombre(entity.getNombre())
                .negocioId(entity.getNegocioId())
                .integra(entity.getIntegra())
                .transfiere(entity.getTransfiere())
                .movimientoStock(entity.getMovimientoStock())
                .cuentaMaestro(entity.getCuentaMaestro())
                .grado(entity.getGrado())
                .grado1(entity.getGrado1())
                .grado2(entity.getGrado2())
                .grado3(entity.getGrado3())
                .grado4(entity.getGrado4())
                .ventas(entity.getVentas())
                .compras(entity.getCompras())
                .gastos(entity.getGastos())
                .build();
    }

    public CuentaEntity toEntity(Cuenta domain) {
        if (domain == null) {
            return null;
        }
        return CuentaEntity.builder()
                .numeroCuenta(domain.getNumeroCuenta())
                .nombre(domain.getNombre())
                .negocioId(domain.getNegocioId())
                .integra(domain.getIntegra())
                .transfiere(domain.getTransfiere())
                .movimientoStock(domain.getMovimientoStock())
                .cuentaMaestro(domain.getCuentaMaestro())
                .grado(domain.getGrado())
                .grado1(domain.getGrado1())
                .grado2(domain.getGrado2())
                .grado3(domain.getGrado3())
                .grado4(domain.getGrado4())
                .ventas(domain.getVentas())
                .compras(domain.getCompras())
                .gastos(domain.getGastos())
                .build();
    }
}
