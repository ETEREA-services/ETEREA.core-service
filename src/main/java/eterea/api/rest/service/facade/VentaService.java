/**
 * 
 */
package eterea.api.rest.service.facade;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
		VentaDTO venta = new VentaDTO(null, clienteMovimiento, cliente, comprobante, null, punto, null,
				articuloMovimientos, valorMovimientos, null, null, null, null, null);
		return venta;
	}

	public VentaDTO findById(String id) {
		String url = MessageFormat.format("http://{0}:{1}/venta", etereaMongoProperties.getServer(),
				etereaMongoProperties.getPort().toString());
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		Mono<VentaDTO> mono = webClient.get().uri(MessageFormat.format("/{0}", id)).retrieve()
				.bodyToMono(VentaDTO.class);
		VentaDTO venta = mono.block();
		try {
			log.debug("Venta.findById -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(venta));
		} catch (JsonProcessingException e) {
			log.debug("Venta.findById -> no se puede deserializar");
		}
		return venta;
	}

	public VentaDTO create(VentaDTO venta) {
		try {
			log.debug("Venta.create before -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(venta));
		} catch (JsonProcessingException e) {
			log.debug("Venta.create before -> no se puede deserializar");
		}
		String url = MessageFormat.format("http://{0}:{1}/venta", etereaMongoProperties.getServer(),
				etereaMongoProperties.getPort().toString());
		WebClient webClient = WebClient.create(url);
		Mono<VentaDTO> mono = webClient.post().uri("/")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(venta), VentaDTO.class).retrieve().bodyToMono(VentaDTO.class);
		venta = mono.block();
		try {
			log.debug("Venta.create after -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(venta));
		} catch (JsonProcessingException e) {
			log.debug("Venta.create after -> no se puede deserializar");
		}
		return venta;
	}

	public VentaDTO update(VentaDTO venta, String id) {
		try {
			log.debug("Venta.update before -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(venta));
		} catch (JsonProcessingException e) {
			log.debug("Venta.update before -> no se puede deserializar");
		}
		String url = MessageFormat.format("http://{0}:{1}/venta", etereaMongoProperties.getServer(),
				etereaMongoProperties.getPort().toString());
		WebClient webClient = WebClient.create(url);
		Mono<VentaDTO> mono = webClient.put().uri("/" + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(venta), VentaDTO.class).retrieve().bodyToMono(VentaDTO.class);
		venta = mono.block();
		try {
			log.debug("Venta.update after -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(venta));
		} catch (JsonProcessingException e) {
			log.debug("Venta.update after -> no se puede deserializar");
		}
		return venta;
	}

}
