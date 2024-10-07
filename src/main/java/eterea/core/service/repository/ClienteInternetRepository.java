/**
 * 
 */
package eterea.core.service.repository;

import eterea.core.service.kotlin.model.ClienteInternet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ClienteInternetRepository extends JpaRepository<ClienteInternet, Long> {

}
