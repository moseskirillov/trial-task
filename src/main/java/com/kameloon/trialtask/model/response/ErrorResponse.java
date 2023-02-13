package com.kameloon.trialtask.model.response;

/**
 * Модель ответа на запрос при ошибке
 *
 * @param error ошибка
 */
public record ErrorResponse(String error) {
}
