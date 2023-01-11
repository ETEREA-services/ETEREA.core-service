/**
 * 
 */
package eterea.api.rest.repository.view.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.view.ArticuloSearch;
import eterea.api.rest.repository.view.IArticuloSearchRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * @author daniel
 *
 */
@Repository
public class IArticuloSearchRepositoryCustomImpl implements IArticuloSearchRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ArticuloSearch> findAllByStrings(List<String> conditions) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ArticuloSearch> criteriaQuery = criteriaBuilder.createQuery(ArticuloSearch.class);
		Root<ArticuloSearch> root = criteriaQuery.from(ArticuloSearch.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		conditions.forEach(condition -> {
			predicates.add(criteriaBuilder.like(root.get("search"), "%" + condition + "%"));
		});
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("descripcion")));
		return entityManager.createQuery(criteriaQuery).setMaxResults(50).getResultList();
	}

}
