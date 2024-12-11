package ru.netology;

public class User {
    private String login;
    private String password;
    private String status;

    public User(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    // Getters и toString() для удобного отображения
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "User{" + "login='" + login + '\\' +
        ", password='" + password + '\\' +
        ", status='" + status + '\\' +
        '}';
    }
}