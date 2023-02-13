package com.kameloon.trialtask.model;

import java.time.LocalDateTime;

/**
 * Модель просмотра цитаты
 *
 * @param id             id цитаты
 * @param userId         id пользователя
 * @param content        содержание цитаты
 * @param dateOfCreation дата создания
 * @param dateOfUpdate   дата обновления
 * @param votes          модель количества лайков и дизлайков
 */
public record ViewQuoteModel(
        Long id,
        Long userId,
        String content,
        LocalDateTime dateOfCreation,
        LocalDateTime dateOfUpdate,
        VoteModel votes
) {
}
