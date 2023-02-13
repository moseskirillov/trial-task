package com.kameloon.trialtask.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Запрос на создание пользователя
 *
 * @param name     имя пользователя
 * @param email    почта пользователя
 * @param password пароль пользователя
 */
public record CreateUserRequest(
        @JsonProperty String name,
        @JsonProperty @NotBlank @Email String email,
        @JsonProperty @NotBlank String password
) {
}
