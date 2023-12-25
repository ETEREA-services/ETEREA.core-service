package eterea.core.api.rest.repository.impl;

import eterea.core.api.rest.model.CuentaMovimiento;
import eterea.core.api.rest.repository.ICuentaMovimientoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ICuentaMovimientoRepositoryCustomImpl implements ICuentaMovimientoRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public ICuentaMovimientoRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BigDecimal calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(Long numeroCuenta, Integer debita, Boolean incluyeInflacion, OffsetDateTime desde, OffsetDateTime hasta) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
        Root<CuentaMovimiento> root = criteriaQuery.from(CuentaMovimiento.class);

        Expression<BigDecimal> sumImporte = criteriaBuilder.coalesce(criteriaBuilder.sum(root.get("importe")), BigDecimal.ZERO);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("numeroCuenta"), numeroCuenta));
        predicates.add(criteriaBuilder.equal(root.get("debita"), debita));
        predicates.add(criteriaBuilder.between(root.get("fecha"), desde, hasta));
        if (!incluyeInflacion) {
            predicates.add(criteriaBuilder.equal(root.get("inflacion"), 0));
        }

        criteriaQuery.select(sumImporte).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}
