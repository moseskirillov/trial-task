package com.kameloon.trialtask.exception;

/**
 * Исключение, возникающее при ошибке, не обработанной другими исключениями
 */
public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message) {
        super(message);
    }
}
