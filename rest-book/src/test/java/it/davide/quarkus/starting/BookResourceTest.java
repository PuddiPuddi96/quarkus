package it.davide.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class BookResourceTest {

    @Test
    void shouldGetAllBooks(){
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/api/books")
                .then()
                .statusCode(200)
                .body("size()", is(3));
    }

    @Test
    void shouldCountAllBooks(){
        given()
                .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)
                .when()
                .get("/api/books/count")
                .then()
                .statusCode(200)
                .body(is("3"));
    }
}
