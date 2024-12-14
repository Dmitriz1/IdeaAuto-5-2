package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {
    @BeforeEach
    void setUp(){
        open("http://localhost:9999");
    }
    @Test
    void shouldTestActive() {
        var validUser = UserGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] input").setValue(validUser.getLogin());
        $("[data-test-id=password] input").setValue(validUser.getPassword());
        $("[data-test-id=action-login]").click();
    }

    @Test
    void shouldTestBlocked() {
        var validUser = UserGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] input").setValue(validUser.getLogin());
        $("[data-test-id=password] input").setValue(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void shouldTestRandomLoginUnregistered() {
        var inValidUser = UserGenerator.Registration.getUser("blocked");
        $("[data-test-id=login] input").setValue(UserGenerator.Registration.getRandomLogin());
        $("[data-test-id=password] input").setValue(inValidUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldTestRandomPassUnregistered() {
        var invalidUser = UserGenerator.Registration.getUser("blocked");
        $("[data-test-id=login] input").setValue(invalidUser.getLogin());
        $("[data-test-id=password] input").setValue(UserGenerator.Registration.getRandomPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldTestRandomPassAndLoginRegistered() {
        $("[data-test-id=login] input").setValue(UserGenerator.Registration.getRandomLogin());
        $("[data-test-id=password] input").setValue(UserGenerator.Registration.getRandomPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldTestRandomLoginRegistered() {
        var validUser = UserGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] input").setValue(UserGenerator.Registration.getRandomLogin());
        $("[data-test-id=password] input").setValue(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldTestRandomPassRegistered() {
        var validUser = UserGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] input").setValue(validUser.getLogin());
        $("[data-test-id=password] input").setValue(UserGenerator.Registration.getRandomPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldTestLoginNotification() {
        var validUser = UserGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] input").setValue("");
        $("[data-test-id=password] input").setValue(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=login].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestPassNotification() {
        var validUser = UserGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] input").setValue(validUser.getLogin());
        $("[data-test-id=password] input").setValue("");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=password].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestBothNotifications() {
        $("[data-test-id=login] input").setValue("");
        $("[data-test-id=password] input").setValue("");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=login].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=password].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }
}