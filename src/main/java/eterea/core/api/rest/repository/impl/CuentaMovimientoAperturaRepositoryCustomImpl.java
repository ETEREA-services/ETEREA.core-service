package eterea.core.api.rest.repository.impl;

import eterea.core.api.rest.kotlin.model.CuentaMovimientoApertura;
import eterea.core.api.rest.repository.CuentaMovimientoAperturaRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class CuentaMovimientoAperturaRepositoryCustomImpl implements CuentaMovimientoAperturaRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public CuentaMovimientoAperturaRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BigDecimal calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(Long numeroCuenta, Integer debita, OffsetDateTime desde, OffsetDateTime hasta) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
        Root<CuentaMovimientoApertura> root = criteriaQuery.from(CuentaMovimientoApertura.class);

        Expression<BigDecimal> sumImporte = criteriaBuilder.coalesce(criteriaBuilder.sum(root.get("importe")), BigDecimal.ZERO);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("numeroCuenta"), numeroCuenta));
        predicates.add(criteriaBuilder.equal(root.get("debita"), debita));
        predicates.add(criteriaBuilder.between(root.get("fecha"), desde, hasta));

        criteriaQuery.select(sumImporte).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}

