package com.kameloon.trialtask.controller;

import com.kameloon.trialtask.model.request.CreateUserRequest;
import com.kameloon.trialtask.model.response.UserResponse;
import com.kameloon.trialtask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для создания пользователя
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Метод создания пользователя
     *
     * @param request запрос на создание
     * @return HTTP code 201
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUserRequest request) {
        userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Метод для получения всех пользователей
     *
     * @return список всех пользователей
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
