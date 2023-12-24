/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eterea.core.api.rest.model;

import eterea.core.api.rest.kotlin.model.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Romina
 */
@Data
@Entity
@Table(name = "grupoproducto", uniqueConstraints = {@UniqueConstraint(columnNames = {"grp_gru_id", "grp_prd_id"})})
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GrupoProducto extends Auditable implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7629544838576508960L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private Long grupoproductoId;

    @Column(name = "grp_gru_id")
    private Integer grupoId;

    @Column(name = "grp_prd_id")
    private Integer productoId;

    @Column(name = "grp_coeficiente")
    @NotNull
    private BigDecimal coeficiente = new BigDecimal(0);

}
