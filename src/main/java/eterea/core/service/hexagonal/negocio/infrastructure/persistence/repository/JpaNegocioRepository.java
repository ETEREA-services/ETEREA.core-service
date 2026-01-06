package eterea.core.service.hexagonal.negocio.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.negocio.infrastructure.persistence.entity.NegocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaNegocioRepository extends JpaRepository<NegocioEntity, Integer> {

    List<NegocioEntity> findAllByCopyArticulo(Byte copyArticulo);
    List<NegocioEntity> findAllByDatabaseIpVpnIsNotNullAndDatabaseIpVpnIsNot(String emptyString);
    List<NegocioEntity> findAllByDatabaseIpVpnIsNotNullAndDatabaseIpVpnIsNotAndNegocioIdIsNot(String emptyString, Integer negocioId);
    Optional<NegocioEntity> findByNegocioId(Integer negocioId);

}
