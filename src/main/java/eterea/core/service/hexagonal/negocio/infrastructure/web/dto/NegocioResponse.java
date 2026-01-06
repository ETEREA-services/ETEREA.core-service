package eterea.core.service.hexagonal.negocio.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NegocioResponse {

    private Integer negocioId;
    private String nombre;
    private Integer negocioIdReal;
    private String databaseIpVpn;
    private String databaseIpLan;
    private String database;
    private String user;
    private Byte transferenciaStock;
    private Byte transferenciaValor;
    private String backendIpVpn;
    private String backendIpLan;
    private String backendPort;
    private Byte hasGateway;
    private Byte copyArticulo;
    private String ipAddress;
    private String backendServer;

}
