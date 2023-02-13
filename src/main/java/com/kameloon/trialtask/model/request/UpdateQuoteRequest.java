package com.kameloon.trialtask.model.request;

/**
 * Запрос на редактирование цитаты
 *
 * @param content новое содержание цитаты
 */
public record UpdateQuoteRequest(String content) {
}
