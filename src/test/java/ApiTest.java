import api.BookingApi;
import api.payload.BookingDates;
import api.payload.BookingRequestPayload;
import api.payload.BookingResponsePayload;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ApiTest extends BaseTest {
    BookingRequestPayload createBookingRequestPayload() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfDate.format(new Date());

        return BookingRequestPayload.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .totalPrice(faker.number().numberBetween(100, 500))
                .depositPaid(true)
                .bookingDates(
                        BookingDates.builder().checkin(currentDate).checkout(currentDate).build())
                .additionalNeeds("None")
                .build();
    }

    @Test
    void testGetBookingByIdReturns200() {
        BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
        int id =
                BookingApi.createBooking(bookingRequestPayload)
                        .as(BookingResponsePayload.class)
                        .getBookingId();
        Response response = BookingApi.getBookingById(id);

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testCreateBookingReturns200() {
        Response response = BookingApi.createBooking(createBookingRequestPayload());

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testCreateBookingReturnsCorrectDetails() {
        BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
        BookingResponsePayload bookingResponsePayload =
                BookingApi.createBooking(bookingRequestPayload).as(BookingResponsePayload.class);

        assertThat(
                bookingRequestPayload.equals(bookingResponsePayload.getBookingRequestPayload()),
                is(true));
    }


    @Test
    void testDeleteBookingReturns201() {
        int id =
                BookingApi.createBooking(createBookingRequestPayload())
                        .as(BookingResponsePayload.class)
                        .getBookingId();

        Response response = BookingApi.deleteBooking(id, token);

        assertThat(response.statusCode(), equalTo(SC_CREATED));
    }
}