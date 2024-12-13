package ru.netology;

import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.builder.RequestSpecBuilder;

import static io.restassured.RestAssured.given;

public class AuthTest {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    void testCreateUser() {
        RegistrationDto user = UserGenerator.generateRandomUser();
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void testCreateExistingUser() {
        RegistrationDto user = new RegistrationDto("vasya", "password", "active");
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);

        // Повторный запрос с тем же логином
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(400);
    }

    @Test
    void testCreateUserWithInvalidLogin() {
        RegistrationDto user = new RegistrationDto("", "password", "active"); // Невалидный логин
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(400);
    }

    @Test
    void testCreateUserWithInvalidPassword() {
        RegistrationDto user = new RegistrationDto("vasya", "", "active"); // Невалидный пароль
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(400);
    }

    @Test
    void testCreateUserWithBlockedStatus() {
        RegistrationDto user = new RegistrationDto("vasya2", "password", "blocked");
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(400);
    }
}