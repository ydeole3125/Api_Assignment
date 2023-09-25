package api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequestPayload {
    @JsonProperty("firstname")
    String firstName;

    @JsonProperty("lastname")
    String lastName;

    @JsonProperty("totalprice")
    int totalPrice;

    @JsonProperty("depositpaid")
    boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    String additionalNeeds;
}
