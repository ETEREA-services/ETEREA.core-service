package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.client.core.ArticuloClient;
import eterea.core.service.client.core.CuentaClient;
import eterea.core.service.client.core.builder.ArticuloClientBuilder;
import eterea.core.service.client.core.builder.CuentaClientBuilder;
import eterea.core.service.client.core.builder.ParametroClientBuilder;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.Negocio;
import eterea.core.service.kotlin.model.dto.ArticuloDto;
import eterea.core.service.kotlin.model.dto.ParametroDto;
import eterea.core.service.service.ArticuloService;
import eterea.core.service.service.NegocioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ArticulosService {

    private final NegocioService negocioService;
    private final ArticuloService articuloService;

    public ArticulosService(NegocioService negocioService,
                            ArticuloService articuloService) {
        this.negocioService = negocioService;
        this.articuloService = articuloService;
    }

    public String getNewCode() {
        return UUID.randomUUID().toString();
    }

    public Boolean replicate(String articuloId) {
        log.debug("Processing replicate");
        var negocios = negocioService.findAllByCopyArticulo((byte) 1);
        logNegocios(negocios);
        var articulo = articuloService.findByArticuloId(articuloId);
        logArticulo(articulo);

        for (var negocio : negocios) {
            var baseUrl = "http://" + negocio.getBackendIpVpn() + ":" + negocio.getBackendPort();
            log.debug("BaseUrl = {}", baseUrl);
            var articuloClient = ArticuloClientBuilder.buildClient(baseUrl);
            var parametroClient = ParametroClientBuilder.buildClient(baseUrl);
            var cuentaClient = CuentaClientBuilder.buildClient(baseUrl);

            if (!articuloExists(articuloClient, articuloId, negocio.getNombre())) {
                var parametro = parametroClient.findTop();
                logParametro(parametro);

                var cuentaVentas = verifyCuenta(cuentaClient, articulo.getCuentaVentas(), "ventas");
                var cuentaCompras = verifyCuenta(cuentaClient, articulo.getCuentaCompras(), "compras");
                var cuentaGastos = verifyCuenta(cuentaClient, articulo.getCuentaGastos(), "gastos");

                var articuloDto = convertArticulo(articulo, parametro, cuentaVentas, cuentaCompras, cuentaGastos);
                logArticuloDto(articuloDto);
                articuloDto = articuloClient.add(articuloDto);
                logArticuloDto(articuloDto);
            }
        }
        return true;
    }

    private boolean articuloExists(ArticuloClient articuloClient, String articuloId, String negocioNombre) {
        try {
            articuloClient.findByArticuloId(articuloId);
            log.debug("Articulo ya existe en -> {}", negocioNombre);
            return true;
        } catch (Exception e) {
            log.debug("Articulo no existe en -> {}", negocioNombre);
            return false;
        }
    }

    private Long verifyCuenta(CuentaClient cuentaClient, Long cuenta, String tipoCuenta) {
        if (cuenta != null) {
            try {
                cuentaClient.findByNumeroCuenta(cuenta);
            } catch (Exception e) {
                log.debug("No existe la cuenta de {}", tipoCuenta);
                return null;
            }
        }
        return cuenta;
    }

    private void logParametro(ParametroDto parametro) {
        try {
            log.debug("ParametroDto -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(parametro));
        } catch (JsonProcessingException e) {
            log.debug("ParametroDto jsonify error -> {}", e.getMessage());
        }
    }

    private void logArticuloDto(ArticuloDto articuloDto) {
        try {
            log.debug("ArticuloDto -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(articuloDto));
        } catch (JsonProcessingException e) {
            log.debug("ArticuloDto jsonify error -> {}", e.getMessage());
        }
    }

    private void logArticulo(Articulo articulo) {
        try {
            log.debug("Articulo -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(articulo));
        } catch (JsonProcessingException e) {
            log.debug("Articulo jsonify error -> {}", e.getMessage());
        }
    }

    private void logNegocios(List<Negocio> negocios) {
        try {
            log.debug("Negocios -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(negocios));
        } catch (JsonProcessingException e) {
            log.debug("Negocios jsonify error -> {}", e.getMessage());
        }
    }

    private ArticuloDto convertArticulo(Articulo articulo, ParametroDto parametro, Long cuentaVentas, Long cuentaCompras, Long cuentaGastos) {
        log.debug("Processing convertArticulo");
        return new ArticuloDto.Builder()
                .articuloId(articulo.getArticuloId())
                .negocioId(articulo.getNegocioId())
                .descripcion(articulo.getDescripcion())
                .leyendaVoucher(articulo.getLeyendaVoucher())
                .precioVentaSinIva(articulo.getPrecioVentaSinIva())
                .precioVentaConIva(articulo.getPrecioVentaConIva())
                .cuentaVentas(cuentaVentas)
                .cuentaCompras(cuentaCompras)
                .cuentaGastos(cuentaGastos)
                .centroStockId(parametro.getCentroStockIdIngreso())
                .rubroId(null)
                .subRubroId(null)
                .precioCompra(articulo.getPrecioCompra())
                .iva105(articulo.getIva105())
                .precioCompraNeto(articulo.getPrecioCompraNeto())
                .exento(articulo.getExento())
                .stockMinimo(articulo.getStockMinimo())
                .stockOptimo(articulo.getStockOptimo())
                .bloqueoCompras(articulo.getBloqueoCompras())
                .bloqueoStock(articulo.getBloqueoStock())
                .bloqueoVentas(articulo.getBloqueoVentas())
                .unidadMedidaId(articulo.getUnidadMedidaId())
                .conDecimales(articulo.getConDecimales())
                .ventas(articulo.getVentas())
                .compras(articulo.getCompras())
                .unidadMedida(articulo.getUnidadMedida())
                .conversionId(articulo.getConversionId())
                .ventaSinStock(articulo.getVentaSinStock())
                .controlaStock(articulo.getControlaStock())
                .asientoCostos(articulo.getAsientoCostos())
                .mascaraBalanza(articulo.getMascaraBalanza())
                .habilitaIngreso(articulo.getHabilitaIngreso())
                .comision(articulo.getComision())
                .prestadorId(articulo.getPrestadorId())
                .build();
    }

}
