/**
 * 
 */
package eterea.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author alma
 *
 */
 @Data
 @Entity
 @Table(name = "articulopolitica", uniqueConstraints = { @UniqueConstraint(columnNames = { "arp_art_id", "arp_pol_id" }) })
 @EqualsAndHashCode(callSuper = false)
 @NoArgsConstructor
 @AllArgsConstructor
public class ArticuloPolitica extends Auditable implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1375278222938563810L;
	 
	@Column(name = "arp_art_id")
	private String articuloId;
	
	@Column(name = "arp_pol_id")
	private Integer politicaId;
	
	@Column(name = "arp_fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime fecha;
	
	@Column(name = "arp_orden")
	private Integer orden;
	
	@Id
	@Column(name = "arp_id")
	private Long articuloPoliticaId;
	
}
