/**
 * 
 */
package eterea.api.rest.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eterea.api.rest.model.Voucher;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface IVoucherRepository extends JpaRepository<Voucher, Long>{
	
	public List<Voucher> findAllByFechaVencimientoAndUsuario(OffsetDateTime fechaVencimiento, String usuario);

	public Optional<Voucher> findByReservaId(Long reservaId);

	Optional<List<Voucher>> findAllByFechaServicio(OffsetDateTime fechaServicio);

}
