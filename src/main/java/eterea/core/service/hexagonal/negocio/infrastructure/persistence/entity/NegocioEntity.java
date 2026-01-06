package eterea.core.service.hexagonal.negocio.infrastructure.persistence.entity;

import eterea.core.service.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "negocio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NegocioEntity extends Auditable {

    @Id
    @Column(name = "neg_id")
    private Integer negocioId;

    @Column(name = "neg_nombre")
    private String nombre;

    @Column(name = "neg_id_real")
    private Integer negocioIdReal;

    @Column(name = "neg_ip")
    private String databaseIpVpn;

    private String databaseIpLan;

    @Column(name = "neg_db")
    private String database;

    @Column(name = "neg_user")
    private String user;

    @Column(name = "neg_transfstock")
    private Byte transferenciaStock;

    @Column(name = "neg_transfvalor")
    private Byte transferenciaValor;

    @Column(name = "backend_server")
    private String backendIpVpn;

    private String backendIpLan;

    private String backendPort;

    private String facturaServer;

    private String facturaPort;

    private Byte hasGateway;

    private Byte copyArticulo;

}
