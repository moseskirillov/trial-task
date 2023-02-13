package com.kameloon.trialtask.exception;

/**
 * Исключение, возникающее при попытке найти несуществующего пользователя
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long message) {
        super(String.valueOf(message));
    }
}
