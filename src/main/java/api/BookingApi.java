package api;
import api.payload.BookingResponsePayload;
import api.payload.BookingRequestPayload;
import utility.ApiEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utility.ApiEndpoints.BASE_URL;
import static utility.ApiEndpoints.BOOKING_ENDPOINT;

public final class BookingApi {
    private static final String ENDPOINT = BASE_URL + BOOKING_ENDPOINT;
    private static final String MEDIA_TYPE_JSON = "application/json";

    private BookingApi() {}

    public static Response getBookingById(int id) {
        return given().accept(MEDIA_TYPE_JSON).when().get(ENDPOINT + id);
    }

    public static Response createBooking(BookingRequestPayload bookingRequestPayload) {
        return given().contentType(ContentType.JSON)
                .accept(MEDIA_TYPE_JSON)
                .body(bookingRequestPayload)
                .when()
                .post(ENDPOINT);
    }

    public static Response updateBooking(
            BookingRequestPayload bookingRequestPayload, int id, String authToken) {
        return given().contentType(ContentType.JSON)
                .accept(MEDIA_TYPE_JSON)
                .header("Cookie", "token=" + authToken)
                .body(bookingRequestPayload)
                .when()
                .put(ENDPOINT + id);
    }

    public static Response deleteBooking(int id, String authToken) {
        return given().header("Cookie", "token=" + authToken).when().delete(ENDPOINT + id);
    }
}
