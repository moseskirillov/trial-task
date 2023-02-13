package com.kameloon.trialtask.model.response;

import com.kameloon.trialtask.model.QuoteModel;

import java.util.List;

/**
 * Модель пользователя при просмотре списка существующих пользователей
 *
 * @param id     id пользователя
 * @param name   имя пользователя
 * @param quotes список цитат пользователя
 */
public record UserResponse(
        Long id,
        String name,
        List<QuoteModel> quotes
) {
}
