package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.persistence.entity;

import eterea.core.service.hexagonal.negocio.infrastructure.persistence.entity.NegocioEntity;
import eterea.core.service.model.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cierrecaja")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CierreCajaEntity extends Auditable {

    @Id
    @Column(name = "cic_id")
    private Long cierreCajaId;

    @Builder.Default
    private Integer puntoVenta = 0;

    @Column(name = "cic_neg_id")
    private Integer negocioId;

    @Column(name = "cic_tope")
    private OffsetDateTime fechaTope;

    @Column(name = "cic_fechahora")
    private OffsetDateTime fechaCierre;

    @Builder.Default
    @Column(name = "cic_usuario")
    private String usuario = "";

    @Builder.Default
    @Column(name = "cic_nivel")
    private Integer nivel = 0;

    @Column(name = "cic_dneg_id")
    private Integer negocioIdDesde;

    @Column(name = "cic_hneg_id")
    private Integer negocioIdHasta;

    @Column(name = "cic_tra_id")
    private Integer transferenciaId;

    @Builder.Default
    @Column(name = "cic_enviado")
    private Byte enviado = 0;

    @Column(name = "clave")
    private Long id;

    @OneToOne(optional = true)
    @JoinColumn(name = "cic_neg_id", insertable = false, updatable = false)
    private NegocioEntity negocio;

}
