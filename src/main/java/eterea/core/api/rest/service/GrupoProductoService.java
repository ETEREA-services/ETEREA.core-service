/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.GrupoProductoException;
import eterea.core.api.rest.kotlin.model.GrupoProducto;
import eterea.core.api.rest.repository.GrupoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class GrupoProductoService {
	@Autowired
	private GrupoProductoRepository repository;

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

	public GrupoProducto update(GrupoProducto newGrupoProducto, Integer grupoId, Integer productoId) {
		return repository.findByUnique(grupoId, productoId).map(grupoproducto -> {
			grupoproducto.setCoeficiente(newGrupoProducto.getCoeficiente());
			return repository.save(grupoproducto);
		}).orElseThrow(() -> new GrupoProductoException(grupoId, productoId));
	}

	public void deleteById(Long grupoproductoId) {
		repository.deleteById(grupoproductoId);
	}
}
