package com.kameloon.trialtask.advice;

import com.kameloon.trialtask.exception.ServerErrorException;
import com.kameloon.trialtask.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Перехватчик фатальных ошибок
 */
@Slf4j
@RestControllerAdvice
public class InternalServerAdvice {

    private static final String FATAL_ERROR_INFO = "Фатальная ошибка сервера: {}";
    private static final String FATAL_ERROR_RESPONSE = "Внутреняя ошибка сервера";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ErrorResponse> internalServerError(ServerErrorException e) {
        log.info(FATAL_ERROR_INFO, e.getMessage());
        var errorResponse = new ErrorResponse(FATAL_ERROR_RESPONSE);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
