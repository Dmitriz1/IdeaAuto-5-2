package ru.netology;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthTest {
    private static RequestSpecification requestSpec;

    @BeforeAll
    static void setUpAll() {
        requestSpec = RestAssured.given()
                .baseUri("http://localhost")
                .port(9999)
                .accept("application/json")
                .contentType("application/json");
    }

    @Test
    void testCreateRandomUser() {
        User user = UserGenerator.generateRandomUser();

        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
