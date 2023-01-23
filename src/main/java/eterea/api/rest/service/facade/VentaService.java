/**
 * 
 */
package eterea.api.rest.service.facade;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import eterea.api.rest.configuration.EtereaMongoProperties;
import eterea.api.rest.model.ArticuloMovimiento;
import eterea.api.rest.model.Cliente;
import eterea.api.rest.model.ClienteMovimiento;
import eterea.api.rest.model.Comprobante;
import eterea.api.rest.model.PuntoVenta;
import eterea.api.rest.model.ValorMovimiento;
import eterea.api.rest.model.dto.VentaDTO;
import eterea.api.rest.service.ArticuloMovimientoService;
import eterea.api.rest.service.ClienteMovimientoService;
import eterea.api.rest.service.PuntoVentaService;
import eterea.api.rest.service.ValorMovimientoService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class VentaService {

	@Autowired
	private ClienteMovimientoService clienteMovimientoService;

	@Autowired
	private ArticuloMovimientoService articuloMovimientoService;

	@Autowired
	private ValorMovimientoService valorMovimientoService;

	@Autowired
	private PuntoVentaService puntoVentaService;

	@Autowired
	private EtereaMongoProperties etereaMongoProperties;

	public VentaDTO findByComprobante(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
		ClienteMovimiento clienteMovimiento = clienteMovimientoService.findByComprobante(comprobanteId, puntoVenta,
				numeroComprobante);
		Cliente cliente = clienteMovimiento.getCliente();
		Comprobante comprobante = clienteMovimiento.getComprobante();
		List<ArticuloMovimiento> articuloMovimientos = articuloMovimientoService
				.findAllByClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
		List<ValorMovimiento> valorMovimientos = valorMovimientoService
				.findAllbyClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
		PuntoVenta punto = puntoVentaService.findByNumero(puntoVenta);
		VentaDTO venta = new VentaDTO(clienteMovimiento, cliente, comprobante, null, punto, null, articuloMovimientos,
				valorMovimientos, null, null, null, null);
		return venta;
	}

	public VentaDTO findById(String id) {
		String url = MessageFormat.format("http://{0}:{1}", etereaMongoProperties.getServer(),
				etereaMongoProperties.getPort().toString());
		WebClient webClient = WebClient.builder().baseUrl(url + "/venta").build();
		Mono<VentaDTO> mono = webClient.get().uri(MessageFormat.format("/{0}", id)).retrieve()
				.bodyToMono(VentaDTO.class);
		// VentaDTO venta = mono.block();
		try {
			log.debug("Venta.findById -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(mono.block()));
		} catch (JsonProcessingException e) {
			log.debug("Venta.findById -> no se puede deserializar");
		}
		return null;
	}

}
