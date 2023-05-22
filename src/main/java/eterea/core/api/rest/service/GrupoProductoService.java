/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.GrupoProductoException;
import eterea.core.api.rest.repository.IGrupoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.GrupoProducto;

/**
 * @author daniel
 *
 */
@Service
public class GrupoProductoService {
	@Autowired
	private IGrupoProductoRepository repository;

	public List<GrupoProducto> findAll() {
		return repository.findAll();
	}
	
	public List<GrupoProducto> findByProductoId(Integer productoId) {
		return repository.findByProductoId(productoId);
	}

	public GrupoProducto findByUnique(Integer grupoId, Integer productoId) {
		return repository.findByUnique(grupoId, productoId).orElseThrow(() -> new GrupoProductoException(grupoId, productoId));
	}

	public GrupoProducto add(GrupoProducto grupoproducto) {
		repository.save(grupoproducto);
		return grupoproducto;
	}

	public GrupoProducto update(GrupoProducto newgrupoproducto, Integer grupoId, Integer productoId) {
		return repository.findByUnique(grupoId, productoId).map(grupoproducto -> {
			grupoproducto.setCoeficiente(newgrupoproducto.getCoeficiente());
			return repository.save(grupoproducto);
		}).orElseThrow(() -> new GrupoProductoException(grupoId, productoId));
	}

	public void deleteById(Long grupoproductoId) {
		repository.deleteById(grupoproductoId);
	}
}
