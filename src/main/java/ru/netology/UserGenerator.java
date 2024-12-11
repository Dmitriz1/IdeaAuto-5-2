package ru.netology;

import java.util.Random;

public class UserGenerator {
    private static final String[] STATUSES = {"active", "blocked"};
    private static final Random RANDOM = new Random();

    public static User generateRandomUser() {
        String login = "user" + RANDOM.nextInt(10000); // Генерация уникального логина
        String password = "password" + RANDOM.nextInt(100); // Генерация пароля
        String status = STATUSES[RANDOM.nextInt(STATUSES.length)]; // Случайный статус

        return new User(login, password, status);
    }
}