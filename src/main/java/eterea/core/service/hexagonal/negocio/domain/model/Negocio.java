package eterea.core.service.hexagonal.negocio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Negocio {

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
    private String facturaServer;
    private String facturaPort;
    private Byte hasGateway;
    private Byte copyArticulo;
    private String ipAddress;
    private String backendServer;

}
