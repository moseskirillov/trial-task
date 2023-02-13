package com.kameloon.trialtask.service;

/**
 * Сервис для работы с лайками и дизлайками
 */
public interface VoteService {
    /**
     * Добавить лайк цитате
     *
     * @param id id цитаты
     */
    void like(Long id);

    /**
     * Добавить дизлайк цитате
     *
     * @param id id цитаты
     */
    void dislike(Long id);
}
