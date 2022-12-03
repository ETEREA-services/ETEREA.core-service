/**
 * Clase mappeada como SuperClass de la que heredan la mayoria de las entidades que utiliza la anotacion
 * AuditingEntityListener para agregar los atributos de creacion y modificacion de tipo LocalDateTime
 */
package eterea.api.rest.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
	@CreatedDate
	@Column(name = "created", nullable = false, updatable = false)
	private LocalDateTime created;

	@LastModifiedDate
	@Column(name = "updated")
	private LocalDateTime updated;
}
