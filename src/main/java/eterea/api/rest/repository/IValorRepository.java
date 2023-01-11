/**
 * 
 */
package eterea.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Valor;

/**
 * @author daniel
 *
 */
@Repository
public interface IValorRepository extends JpaRepository<Valor, Integer> {

}
