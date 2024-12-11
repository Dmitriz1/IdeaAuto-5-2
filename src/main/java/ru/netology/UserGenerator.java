package ru.netology;

import java.util.Random;

public class UserGenerator {
    private static final String[] statuses = {"active", "blocked"};
    private static final Random random = new Random();

    public static RegistrationDto generateRandomUser() {
        String login = "user" + random.nextInt(1000); // Генерация случайного логина
        String password = "password" + random.nextInt(100); // Генерация случайного пароля
        String status = statuses[random.nextInt(statuses.length)]; // Случайный статус

        return new RegistrationDto(login, password, status);
    }
}