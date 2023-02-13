package com.kameloon.trialtask.advice;

import com.kameloon.trialtask.exception.UserAlreadyExistException;
import com.kameloon.trialtask.exception.UserNotFoundException;
import com.kameloon.trialtask.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Перехватчик исключений работы с пользователем
 */
@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {

    private static final String USER_ALREADY_EXIST_INFO = "Пользователь уже существует";
    private static final String USER_ALREADY_EXIST_ERROR = "Попытка создания уже существующего пользователя с email: {}";
    private static final String USER_NOT_FOUND_INFO = "Пользователь не найден";
    private static final String USER_NOT_FOUND_ERROR = "Пользователь c id {} не найден";

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> userAlreadyExistAdvice(UserAlreadyExistException e) {
        log.info(USER_ALREADY_EXIST_ERROR, e.getMessage());
        var errorResponse = new ErrorResponse(USER_ALREADY_EXIST_INFO);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException e) {
        log.info(USER_NOT_FOUND_ERROR, e.getMessage());
        var errorResponse = new ErrorResponse(USER_NOT_FOUND_INFO);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> argumentNotValid(MethodArgumentNotValidException e) {
        var errors = new HashMap<String, String>();
        e.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
