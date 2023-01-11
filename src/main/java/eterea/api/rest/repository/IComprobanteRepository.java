/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Comprobante;

/**
 * @author daniel
 *
 */
@Repository
public interface IComprobanteRepository extends JpaRepository<Comprobante, Integer> {

	public List<Comprobante> findAllByFacturaElectronicaAndAsociado(Byte facturaElectronica, Byte Asociado);

	public List<Comprobante> findAllByModuloAndTransferirAndInvisible(Integer modulo, Byte transferir, Byte invisible);

	public List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(Integer modulo,
			Byte transferir, Byte invisible, Byte debita, Integer comprobanteId);

	public List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndDebita(Integer modulo, Byte transferir,
			Byte invisible, Byte debita);

	public List<Comprobante> findAllByModuloAndTransferirAndInvisibleAndComprobanteId(Integer modulo, Byte transferir,
			Byte invisible, Integer comprobanteId);

	public List<Comprobante> findAllByModuloAndDebitaAndAsociado(Integer modulo, Byte debita, Byte asociado);

	public List<Comprobante> findAllByModuloAndAplicaPendienteAndReciboAndLetraComprobanteAndNivelLessThanEqualAndInvisibleOrderByDescripcion(
			Integer modulo, Byte aplicaPendiente, Byte recibo, String letraComprobante, Integer nivel, Byte invisible);

	public Optional<Comprobante> findByComprobanteId(Integer comprobanteId);

}
