package com.kameloon.trialtask.service;

import com.kameloon.trialtask.model.request.CreateUserRequest;
import com.kameloon.trialtask.model.response.UserResponse;

import java.util.List;

/**
 * Сервис создания пользователя
 */
public interface UserService {
    /**
     * Создание пользователя
     *
     * @param request запрос на создание
     */
    void create(CreateUserRequest request);

    List<UserResponse> findAll();
}
