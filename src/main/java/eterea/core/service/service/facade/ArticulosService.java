package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.client.core.ArticuloBarraClient;
import eterea.core.service.client.core.ArticuloClient;
import eterea.core.service.client.core.CuentaClient;
import eterea.core.service.client.core.ParametroClient;
import eterea.core.service.client.core.builder.ArticuloBarraClientBuilder;
import eterea.core.service.client.core.builder.ArticuloClientBuilder;
import eterea.core.service.client.core.builder.CuentaClientBuilder;
import eterea.core.service.client.core.builder.ParametroClientBuilder;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.ArticuloBarra;
import eterea.core.service.kotlin.model.Negocio;
import eterea.core.service.kotlin.model.dto.ArticuloDto;
import eterea.core.service.kotlin.model.dto.ParametroDto;
import eterea.core.service.service.ArticuloBarraService;
import eterea.core.service.service.ArticuloService;
import eterea.core.service.service.NegocioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class ArticulosService {

    private final NegocioService negocioService;
    private final ArticuloService articuloService;
    private final ArticuloBarraService articuloBarraService;

    public ArticulosService(NegocioService negocioService,
                            ArticuloService articuloService, ArticuloBarraService articuloBarraService) {
        this.negocioService = negocioService;
        this.articuloService = articuloService;
        this.articuloBarraService = articuloBarraService;
    }

    public String getNewCode() {
        return UUID.randomUUID().toString();
    }

    public Boolean replicate(String articuloId) {
        log.debug("Starting article replication for articleId: {}", articuloId);

        List<Negocio> negocios = negocioService.findAllByCopyArticulo((byte) 1);
        Articulo articulo = articuloService.findByArticuloId(articuloId);
        List<ArticuloBarra> barrasToReplicate = articuloBarraService.findAllByArticuloId(articuloId);

        negocios.forEach(negocio -> log.debug("Negocio -> {}", negocio.jsonify()));
        log.debug("Articulo -> {}", articulo.jsonify());

        for (Negocio negocio : negocios) {
            try {
                replicateToNegocio(negocio, articulo, barrasToReplicate);
            } catch (Exception e) {
                log.error("Failed to replicate article to business {}: {}", negocio.getNombre(), e.getMessage());
            }
        }

        return true;
    }

    private void replicateToNegocio(Negocio negocio, Articulo articulo, List<ArticuloBarra> barrasToReplicate) {
        String baseUrl = String.format("http://%s:%s", negocio.getBackendIpVpn(), negocio.getBackendPort());
        log.debug("Replicating to business {} at {}", negocio.getNombre(), baseUrl);

        // Inicializar clientes
        var clients = initializeClients(baseUrl);

        // Verificar si existe y actualizar si es necesario
        if (articuloExists(clients.articuloClient, articulo.getArticuloId(), negocio.getNombre())) {
            updateArticuloIfNeeded(clients, articulo);
        } else {
            replicateArticulo(clients, articulo);
        }

        // Replicar códigos de barras
        replicateCodigosBarras(clients.articuloBarraClient, barrasToReplicate, articulo.getArticuloId());
    }

    private void updateArticuloIfNeeded(ClientsHolder clients, Articulo articulo) {
        try {
            var existingArticulo = clients.articuloClient.findByArticuloId(articulo.getArticuloId());

            if (!Objects.equals(existingArticulo.getDescripcion(), articulo.getDescripcion())) {
                
                log.debug("Article {} needs update. Existing values: desc={}, priceNoIva={}, priceWithIva={}. New values: desc={}, priceNoIva={}, priceWithIva={}",
                    articulo.getArticuloId(),
                    existingArticulo.getDescripcion(),
                    existingArticulo.getPrecioVentaSinIva(),
                    existingArticulo.getPrecioVentaConIva(),
                    articulo.getDescripcion(),
                    articulo.getPrecioVentaSinIva(),
                    articulo.getPrecioVentaConIva());

                // Update only the fields that need to be updated
                existingArticulo.setDescripcion(articulo.getDescripcion());

                clients.articuloClient.update(existingArticulo, articulo.getArticuloId());
                log.debug("Article {} updated successfully", articulo.getArticuloId());
            } else {
                log.debug("Article {} doesn't need update", articulo.getArticuloId());
            }
        } catch (Exception e) {
            log.error("Error updating article {}: {}", articulo.getArticuloId(), e.getMessage());
        }
    }

    private ClientsHolder initializeClients(String baseUrl) {
        return new ClientsHolder(
                ArticuloClientBuilder.buildClient(baseUrl),
                ArticuloBarraClientBuilder.buildClient(baseUrl),
                ParametroClientBuilder.buildClient(baseUrl),
                CuentaClientBuilder.buildClient(baseUrl)
        );
    }

    private void replicateArticulo(ClientsHolder clients, Articulo articulo) {
        var parametro = clients.parametroClient.findTop();
        log.debug("Parametro -> {}", parametro.jsonify());

        var cuentaVentas = verifyCuenta(clients.cuentaClient, articulo.getCuentaVentas(), "ventas");
        var cuentaCompras = verifyCuenta(clients.cuentaClient, articulo.getCuentaCompras(), "compras");
        var cuentaGastos = verifyCuenta(clients.cuentaClient, articulo.getCuentaGastos(), "gastos");

        var articuloDto = convertArticulo(articulo, parametro, cuentaVentas, cuentaCompras, cuentaGastos);
        log.debug("ArticuloDto -> {}", articuloDto.jsonify());

        articuloDto = clients.articuloClient.add(articuloDto);
        log.debug("ArticuloDto (post) -> {}", articuloDto.jsonify());
    }

    private void replicateCodigosBarras(ArticuloBarraClient client, List<ArticuloBarra> barrasToReplicate, String articuloId) {
        log.debug("Processing replicateCodigosBarras");
        try {
            log.debug("A Replicar");
            barrasToReplicate.forEach(barra -> log.debug("ArticuloBarra -> {}", barra.jsonify()));
            var barrasExistentes = client.findAllByArticuloId(articuloId);
            log.debug("Existentes");
            barrasExistentes.forEach(barra -> log.debug("ArticuloBarra -> {}", barra.jsonify()));

            // Eliminar códigos obsoletos
            deleteObsoleteBarCodes(client, barrasExistentes, barrasToReplicate);

            // Agregar nuevos códigos
            addNewBarCodes(client, barrasToReplicate, barrasExistentes);
        } catch (Exception e) {
            log.error("Error processing bar codes: {}", e.getMessage());
            throw new RuntimeException("Failed to replicate bar codes", e);
        }
    }

    private void deleteObsoleteBarCodes(ArticuloBarraClient client, List<ArticuloBarra> existing, List<ArticuloBarra> toReplicate) {
        log.debug("Processing deleteObsoleteBarCodes");
        if (toReplicate.isEmpty()) {
            // Si no hay códigos para replicar, eliminar todos los existentes
            existing.forEach(barra -> {
                try {
                    client.delete(barra.getCodigoBarras());
                    log.debug("Deleted bar code: {}", barra.getCodigoBarras());
                } catch (Exception e) {
                    log.error("Error deleting bar code {}: {}", barra.getCodigoBarras(), e.getMessage());
                }
            });
            return;
        }
    
        // Comportamiento original para cuando hay códigos para replicar
        existing.stream()
                .filter(existingBarra -> toReplicate.stream()
                        .noneMatch(newBarra -> Objects.equals(existingBarra.getCodigoBarras(), newBarra.getCodigoBarras())))
                .forEach(barra -> {
                    try {
                        client.delete(barra.getCodigoBarras());
                        log.debug("Deleted bar code: {}", barra.getCodigoBarras());
                    } catch (Exception e) {
                        log.error("Error deleting bar code {}: {}", barra.getCodigoBarras(), e.getMessage());
                    }
                });
    }
    private void addNewBarCodes(ArticuloBarraClient client, List<ArticuloBarra> toReplicate, List<ArticuloBarra> existing) {
        log.debug("Processing addNewBarCodes");
        toReplicate.stream()
                .filter(newBarra -> existing.stream()
                        .noneMatch(existingBarra -> Objects.equals(newBarra.getCodigoBarras(), existingBarra.getCodigoBarras())))
                .forEach(barra -> {
                    try {
                        barra.setArticuloBarraId(null);
                        client.add(barra);
                        log.debug("Added bar code: {}", barra.getCodigoBarras());
                    } catch (Exception e) {
                        log.error("Error adding bar code {}: {}", barra.getCodigoBarras(), e.getMessage());
                    }
                });
    }

    private record ClientsHolder(ArticuloClient articuloClient, ArticuloBarraClient articuloBarraClient,
                                 ParametroClient parametroClient, CuentaClient cuentaClient) {
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
