/**
 * 
 */
package eterea.core.api.rest.model.client;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

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
public class OrderWeb implements Serializable {

	private static final long serialVersionUID = 4300670549392377279L;

	@SerializedName("order_number")
	private Long orderNumber;

	@SerializedName("order_status")
	private String orderStatus = "";

	@SerializedName("order_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime orderDate;

	@SerializedName("paid_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime paidDate;

	@SerializedName("completed_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime completedDate;

	@SerializedName("modified_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private OffsetDateTime modifiedDate;

	@SerializedName("order_currency")
	private String orderCurrency = "";

	@SerializedName("customer_note")
	private String customerNote = "";

	@SerializedName("billing_first_name")
	private String billingFirstName = "";

	@SerializedName("billing_last_name")
	private String billingLastName = "";

	@SerializedName("billing_full_name")
	private String billingFullName = "";

	@SerializedName("_billing_dni_o_pasaporte")
	private String billingDnioPasaporte = "";

	@SerializedName("billing_address")
	private String billingAddress = "";

	@SerializedName("billing_city")
	private String billingCity = "";

	@SerializedName("billing_state")
	private String billingState = "";

	@SerializedName("billing_postcode")
	private String billingPostcode = "";

	@SerializedName("billing_country")
	private String billingCountry = "";

	@SerializedName("billing_email")
	private String billingEmail = "";

	@SerializedName("billing_phone")
	private String billingPhone = "";

	@SerializedName("shipping_first_name")
	private String shippingFirstName = "";

	@SerializedName("shipping_last_name")
	private String shippingLastName = "";

	@SerializedName("shipping_full_name")
	private String shippingFullName = "";

	@SerializedName("shipping_address")
	private String shippingAddress = "";

	@SerializedName("shipping_city")
	private String shippingCity = "";

	@SerializedName("shipping_state")
	private String shippingState = "";

	@SerializedName("shipping_postcode")
	private String shippingPostcode = "";

	@SerializedName("shipping_country_full")
	private String shippingCountryFull = "";

	@SerializedName("payment_method_title")
	private String paymentMethodTitle = "";

	@SerializedName("cart_discount")
	private String cartDiscount = "";

	@SerializedName("order_subtotal")
	private BigDecimal orderSubtotal;

	@SerializedName("order_subtotal_refunded")
	private BigDecimal orderSubtotalRefunded;

	@SerializedName("shipping_method_title")
	private String shippingMethodTitle = "";

	@SerializedName("order_shipping")
	private String orderShipping = "";

	@SerializedName("order_shipping_refunded")
	private BigDecimal orderShippingRefunded;

	@SerializedName("order_total")
	private BigDecimal orderTotal;

	@SerializedName("order_total_tax")
	private BigDecimal orderTotalTax;

	private List<ProductWeb> products;

	@SerializedName("order_notes")
	private String orderNotes = "";

}
