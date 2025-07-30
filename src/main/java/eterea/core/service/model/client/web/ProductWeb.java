/**
 * 
 */
package eterea.core.service.model.client.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWeb implements Serializable {

	private static final long serialVersionUID = 887121563543149434L;

	private String sku;

	@SerializedName("line_id")
	private Integer lineId;

	private String name = "";
	private Integer qty;

	@SerializedName("item_price")
	private BigDecimal itemPrice;

	@SerializedName("booking_start")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime bookingStart;

	@SerializedName("booking_end")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime bookingEnd;

	@SerializedName("booking_duration")
	private Integer bookingDuration;

	@SerializedName("booking_persons")
	private Integer bookingPersons;

	@SerializedName("person_types")
	private String personTypes = "";

	@SerializedName("servicios_adicionales")
	private String serviciosAdicionales = "";

	@SerializedName("punto_de_encuentro")
	private String puntoDeEncuentro = "";

}
