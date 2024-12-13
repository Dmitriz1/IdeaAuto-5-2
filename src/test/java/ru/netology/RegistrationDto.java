package ru.netology;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class RegistrationDto {
    @SerializedName("login")
    String login;

    @SerializedName("password")
    String password;

    @SerializedName("status")
    String status;
}