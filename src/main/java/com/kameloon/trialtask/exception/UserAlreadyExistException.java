package com.kameloon.trialtask.exception;

/**
 * Исключение, возникающее при попытке создать уже существующего пользователя
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
