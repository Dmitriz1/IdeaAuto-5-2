package ru.netology;

import java.util.Random;

class UserGenerator {
    private static final String[] statuses = {"active", "blocked"};
    private static final Random random = new Random();
    public static RegistrationDto generateRandomUser() {
        String login = "user" + random.nextInt(10000);
        String password = "password" + random.nextInt(100);;
        String status = statuses[random.nextInt(statuses.length)];
        return new RegistrationDto(login, password, status);
    }
}