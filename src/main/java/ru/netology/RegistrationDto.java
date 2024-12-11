package ru.netology;

import com.google.gson.annotations.SerializedName;

public class RegistrationDto {
    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    @SerializedName("status")
    private String status;

    public RegistrationDto(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}