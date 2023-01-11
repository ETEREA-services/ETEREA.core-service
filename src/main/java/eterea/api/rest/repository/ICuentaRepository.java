/**
 * 
 */
package eterea.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Cuenta;

/**
 * @author daniel
 *
 */
@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {

}
