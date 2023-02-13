package com.kameloon.trialtask.model;

/**
 * Модель цитаты
 *
 * @param id      id цитаты
 * @param content содержание цитаты
 */
public record QuoteModel(
        Long id,
        String content
) {
}
