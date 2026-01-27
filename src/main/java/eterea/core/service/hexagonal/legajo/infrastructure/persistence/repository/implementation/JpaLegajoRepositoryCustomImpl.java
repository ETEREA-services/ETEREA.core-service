package eterea.core.service.hexagonal.legajo.infrastructure.persistence.repository.implementation;

import eterea.core.service.hexagonal.legajo.infrastructure.persistence.entity.LegajoEntity;
import eterea.core.service.hexagonal.legajo.infrastructure.persistence.repository.JpaLegajoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JpaLegajoRepositoryCustomImpl implements JpaLegajoRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<LegajoEntity> findAllByStrings(List<String> conditions) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LegajoEntity> query = criteriaBuilder.createQuery(LegajoEntity.class);
        Root<LegajoEntity> root = query.from(LegajoEntity.class);

        // Construct the search expression: leg_id + ' ' + leg_nombre + ' ' + leg_apellido
        Expression<String> search = criteriaBuilder.concat(root.get("legajoId").as(String.class), " ");
        search = criteriaBuilder.concat(search, root.get("nombre"));
        search = criteriaBuilder.concat(search, " ");
        search = criteriaBuilder.concat(search, root.get("apellido"));

        List<Predicate> predicates = new ArrayList<>();
        if (conditions != null) {
            for (String condition : conditions) {
                predicates.add(criteriaBuilder.like(search, "%" + condition + "%"));
            }
        }

        query.select(root);
        
        if (!predicates.isEmpty()) {
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.orderBy(criteriaBuilder.asc(root.get("apellido")), criteriaBuilder.asc(root.get("nombre")));
        
        return entityManager.createQuery(query).setMaxResults(50).getResultList();
    }
}
