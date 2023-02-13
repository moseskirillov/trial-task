package com.kameloon.trialtask.model.request;

/**
 * Запрос на создание цитаты
 *
 * @param userId  id пользователя
 * @param content содержание цитаты
 */
public record CreateQuoteRequest(
        Long userId,
        String content
) {
}
