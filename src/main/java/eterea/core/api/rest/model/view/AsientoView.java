package eterea.core.api.rest.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "vw_asientos")
@NoArgsConstructor
@AllArgsConstructor
public class AsientoView implements Serializable {

    @Id
    private String uniqueId = null;
    private Integer negocioId = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fecha= null;
    @Column(name = "nrocomp")
    private Integer orden = 0;
    @Column(name = "cgotcomp")
    private Integer comprobanteId = null;
    private String concepto = "";
    @Column(name = "cic_id")
    private Long cierreCajaId = null;
    private BigDecimal debe = BigDecimal.ZERO;
    private BigDecimal haber = BigDecimal.ZERO;

}
