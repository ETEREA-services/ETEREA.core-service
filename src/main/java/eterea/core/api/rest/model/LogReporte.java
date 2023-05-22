/**
 * 
 */
package eterea.core.api.rest.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "logreporte")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LogReporte extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8704281828815039777L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logReporteId;
	
	@Column(name = "ire_user")
	private String user;
	
	@Column(name = "ire_ip")
	private String ipAddress;
	
	@Column(name = "ire_when")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime when;
	
	@Column(name = "ire_report")
	private String report;
	
	@Column(name = "ire_stringconnection")
	private String stringConnection; 
	
	@Column(name = "ire_parameters")
	private String parameters;
}
