package eterea.core.service.hexagonal.cuenta.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eterea.core.service.model.Auditable;
import eterea.core.service.tool.Jsonifier;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "plancta")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CuentaEntity extends Auditable {

    @Id
    @Column(name = "cuenta")
    private Long numeroCuenta;

    private String nombre;

    @Column(name = "pla_neg_id")
    private Integer negocioId;

    private Byte integra;

    @Column(name = "pla_transfiere")
    private Byte transfiere;

    @Column(name = "pla_movstock")
    private Byte movimientoStock;

    @Builder.Default
    @Column(name = "pla_cuentamaestro")
    private BigDecimal cuentaMaestro = BigDecimal.ZERO;

    @Column(name = "pla_grado")
    private Integer grado;

    @Column(name = "pla_grado1")
    private Long grado1;

    @Column(name = "pla_grado2")
    private Long grado2;

    @Column(name = "pla_grado3")
    private Long grado3;

    @Column(name = "pla_grado4")
    private Long grado4;

    @Column(name = "pla_ventas")
    private Byte ventas;

    @Column(name = "pla_compras")
    private Byte compras;

    @Column(name = "pla_gastos")
    private Byte gastos;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
