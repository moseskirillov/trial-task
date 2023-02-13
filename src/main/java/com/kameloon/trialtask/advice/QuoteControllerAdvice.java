package com.kameloon.trialtask.advice;

import com.kameloon.trialtask.exception.QuoteNotFoundException;
import com.kameloon.trialtask.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Перехватчик ошибок работы с цитатами
 */
@Slf4j
@RestControllerAdvice
public class QuoteControllerAdvice {

    private static final String QUOTE_NOT_FOUND_ERROR = "Не найдено цитаты с id %s";
    private static final String QUOTE_NOT_FOUND_INFO = "Попытка редактирования несуществующей цитаты с id {}";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<ErrorResponse> quoteNotFoundException(QuoteNotFoundException e) {
        log.info(QUOTE_NOT_FOUND_INFO, e.getMessage());
        var errorResponse = new ErrorResponse(String.format(QUOTE_NOT_FOUND_ERROR, e.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
