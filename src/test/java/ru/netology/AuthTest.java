package ru.netology;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {
    private static RequestSpecification requestSpec;

    @BeforeAll
    static void setUpAll() {
        requestSpec = RestAssured.with()
                .baseUri("http://localhost")
                .port(9999)
                .contentType("application/json")
                .accept("application/json");
    }

    @Test
    public void testCreateUser() {
        RegistrationDto user = UserGenerator.generateRandomUser();

        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateUserWithBlockedStatus() {
        RegistrationDto user = new RegistrationDto("blockedUser", "password123", "blocked");

        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(404);
    }
    @Test
    public void testCreateUserWithInvalidLogin() {
        RegistrationDto user = new RegistrationDto("", "password123", "active"); // Невалидный логин

        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateUserWithInvalidPassword() {
        RegistrationDto user = new RegistrationDto("userInvalid", "", "active"); // Невалидный пароль

        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(404);
    }
}
