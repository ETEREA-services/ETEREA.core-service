package eterea.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ReservaArticulo;

@Repository
public interface IReservaArticuloRepository extends JpaRepository<ReservaArticulo, Long> {

	public List<ReservaArticulo> findAllByReservaId(Long reservaId);
}