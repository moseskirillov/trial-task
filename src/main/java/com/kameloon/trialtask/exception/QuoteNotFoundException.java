package com.kameloon.trialtask.exception;

/**
 * Исключение, возникающее при попытке найти несуществующую цитату
 */
public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException(String message) {
        super(message);
    }
}
