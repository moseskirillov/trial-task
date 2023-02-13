package com.kameloon.trialtask.service;

import com.kameloon.trialtask.model.request.CreateQuoteRequest;
import com.kameloon.trialtask.model.request.CreateQuoteResponse;
import com.kameloon.trialtask.model.request.UpdateQuoteRequest;
import com.kameloon.trialtask.model.ViewQuoteModel;

import java.util.List;

/**
 * Сервис CRUD для цитат
 */
public interface QuoteService {
    /**
     * Добавление новой цитаты
     *
     * @param request запрос на создание
     */
    CreateQuoteResponse create(CreateQuoteRequest request);

    /**
     * Получение цитаты по id
     *
     * @param id id цитаты
     * @return модель цитаты
     */
    ViewQuoteModel findById(Long id);

    /**
     * Редактирование цитаты по id
     *
     * @param id      id цитаты
     * @param request запрос на обновление
     */
    void update(Long id, UpdateQuoteRequest request);

    /**
     * Удаление цитаты
     *
     * @param id id цитаты
     */
    void deleteById(Long id);

    /**
     * Получение рандомной цитаты
     *
     * @return цитата
     */
    ViewQuoteModel getRandom();

    /**
     * Получение 10 лучших цитат
     *
     * @return список цитат
     */
    List<ViewQuoteModel> getTopByLikes();

    /**
     * Получение 10 худших цитат
     *
     * @return список цитат
     */
    List<ViewQuoteModel> getTopByDislikes();
}
