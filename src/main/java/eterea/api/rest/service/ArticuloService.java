/**
 * 
 */
package eterea.api.rest.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ArticuloBarraNotFoundException;
import eterea.api.rest.exception.ArticuloNotFoundException;
import eterea.api.rest.model.Articulo;
import eterea.api.rest.model.ArticuloBarra;
import eterea.api.rest.model.dto.ArticuloPesoDTO;
import eterea.api.rest.model.view.ArticuloSearch;
import eterea.api.rest.repository.IArticuloRepository;
import eterea.api.rest.service.view.ArticuloSearchService;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloService {

	@Autowired
	private IArticuloRepository repository;

	@Autowired
	private ArticuloSearchService articuloSearchService;

	@Autowired
	private ArticuloCentroService articuloCentroService;

	@Autowired
	private ArticuloBarraService articuloBarraService;

	@Autowired
	private CuentaService cuentaService;

	public List<Articulo> findAll() {
		return repository.findAll();
	}

	public List<ArticuloSearch> findAllByStrings(List<String> conditions) {
		return articuloSearchService.findAllByStrings(conditions);
	}

	public List<ArticuloSearch> findAllVentaByStrings(List<String> conditions) {
		List<Long> numeroCuentas = cuentaService.findAll().stream().map(cuenta -> cuenta.getNumeroCuenta())
				.collect(Collectors.toList());
		List<String> articuloIds = articuloCentroService.findAll().stream().map(articulo -> articulo.getArticuloId())
				.collect(Collectors.toList());
		List<ArticuloSearch> articuloSearchs = articuloSearchService.findAllByStrings(conditions);
		articuloSearchs = articuloSearchs.stream().filter(articulo -> articuloIds.contains(articulo.getArticuloId()))
				.collect(Collectors.toList());
		// Sólo los que tengan cuenta contable ventas
		articuloSearchs = articuloSearchs.stream()
				.filter(articulo -> numeroCuentas.contains(articulo.getNumeroCuentaVentas()))
				.collect(Collectors.toList());
		//
		articuloSearchs = articuloSearchs.stream()
				.filter(articulo -> articulo.getBloqueoVentas() == 0
						&& articulo.getPrecioVentaConIva().compareTo(BigDecimal.ZERO) != 0)
				.collect(Collectors.toList());
		return articuloSearchs;
	}

	public Articulo findByArticuloId(String articuloId) {
		return repository.findByArticuloId(articuloId).orElseThrow(() -> new ArticuloNotFoundException(articuloId));
	}

	private Articulo findByMascaraBalanza(String mascaraBalanza) {
		return repository.findByMascaraBalanza(mascaraBalanza)
				.orElseThrow(() -> new ArticuloNotFoundException(mascaraBalanza));
	}

	private Articulo findByCodigoBarras(String codigoBarras) {

		if (codigoBarras.trim().equals("")) {
			throw new ArticuloNotFoundException(codigoBarras);
		}

		ArticuloBarra articuloBarra = null;
		try {
			articuloBarra = articuloBarraService.findByCodigoBarras(codigoBarras);
		} catch (ArticuloBarraNotFoundException e) {
			throw new ArticuloNotFoundException(codigoBarras);
		}

		return articuloBarra.getArticulo();
	}

	public Articulo findByUniqueId(Long uniqueId) {
		return repository.findByUniqueId(uniqueId).orElseThrow(() -> new ArticuloNotFoundException(uniqueId));
	}

	public ArticuloPesoDTO findArticuloAndPeso(String codigo) {
		ArticuloPesoDTO articuloPeso = new ArticuloPesoDTO();
		try {
			articuloPeso.setArticulo(findByArticuloId(codigo));
			return articuloPeso;
		} catch (ArticuloNotFoundException e) {
		}
		try {
			articuloPeso.setArticulo(findByCodigoBarras(codigo));
			return articuloPeso;
		} catch (ArticuloNotFoundException e) {
		}

		if (codigo.length() == 13) {
			String codigoPropietarioInicial = codigo.substring(0, 1);
			String codigoPropietarioCodigo = codigo.substring(2, 6);
			String codigoPropietarioPeso = codigo.substring(7, 11);
//			String codigoPropietarioValidador = codigo.substring(12, 12);

//			Verifica si es un código de barras propio
			if (codigoPropietarioInicial.equals("25")) {
				try {
					articuloPeso.setArticulo(findByArticuloId(codigoPropietarioCodigo));
					articuloPeso.setPeso(new BigDecimal(Double.parseDouble(codigoPropietarioPeso))
							.divide(new BigDecimal(1000)).setScale(3, RoundingMode.HALF_UP));
					return articuloPeso;
				} catch (ArticuloNotFoundException e) {
				}

				try {
					articuloPeso.setArticulo(findByMascaraBalanza(codigoPropietarioCodigo));
					articuloPeso.setPeso(new BigDecimal(Double.parseDouble(codigoPropietarioPeso))
							.divide(new BigDecimal(1000)).setScale(3, RoundingMode.HALF_UP));
					return articuloPeso;
				} catch (ArticuloNotFoundException e) {
				}
			}

//	         Verifica si es un código de carnes de mi campo
			if (!(codigoPropietarioInicial.equals("20") || codigoPropietarioInicial.equals("23"))) {
				return articuloPeso;
			}

			try {
				articuloPeso.setArticulo(findByCodigoBarras(codigoPropietarioCodigo));
				articuloPeso.setPeso(BigDecimal.ZERO);
				if (codigoPropietarioInicial.equals("23")) {
					articuloPeso.setPeso(new BigDecimal(Double.parseDouble(codigoPropietarioPeso))
							.divide(new BigDecimal(100)).setScale(3, RoundingMode.HALF_UP));
				}
				if (codigoPropietarioInicial.equals("20")) {
					articuloPeso.setPeso(new BigDecimal(Double.parseDouble(codigoPropietarioPeso))
							.divide(new BigDecimal(1000)).setScale(3, RoundingMode.HALF_UP));
				}
				return articuloPeso;
			} catch (ArticuloNotFoundException e) {
			}
		}
		return articuloPeso;
	}

}
