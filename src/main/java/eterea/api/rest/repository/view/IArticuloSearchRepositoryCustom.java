/**
 * 
 */
package eterea.api.rest.repository.view;

import java.util.List;

import eterea.api.rest.model.view.ArticuloSearch;

/**
 * @author daniel
 *
 */
public interface IArticuloSearchRepositoryCustom {

	public List<ArticuloSearch> findAllByStrings(List<String> conditions);

}
