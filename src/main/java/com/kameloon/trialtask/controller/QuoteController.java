package com.kameloon.trialtask.controller;

import com.kameloon.trialtask.model.ViewQuoteModel;
import com.kameloon.trialtask.model.request.CreateQuoteRequest;
import com.kameloon.trialtask.model.request.CreateQuoteResponse;
import com.kameloon.trialtask.model.request.UpdateQuoteRequest;
import com.kameloon.trialtask.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер CRUD для сущности цитат
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    /**
     * Добавление новой цитаты
     *
     * @param request запрос
     * @return HTTP 201
     */
    @PostMapping
    public ResponseEntity<CreateQuoteResponse> create(@RequestBody CreateQuoteRequest request) {
        return ResponseEntity.ok(quoteService.create(request));
    }

    /**
     * Получение цитаты по id
     *
     * @param id id цитаты
     * @return модель цитаты
     */
    @GetMapping("{id}")
    public ResponseEntity<ViewQuoteModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.findById(id));
    }

    /**
     * Редактирование цитаты
     *
     * @param request запрос с информацией для редактирования
     * @param id      id цитаты
     * @return HTTP 200
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody UpdateQuoteRequest request, @PathVariable Long id) {
        quoteService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Удаление цитаты по id
     *
     * @param id id цитаты
     * @return HTTP 200
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        quoteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Получение рандомной цитаты
     *
     * @return модель цитаты
     */
    @GetMapping
    public ResponseEntity<ViewQuoteModel> getRandom() {
        return ResponseEntity.ok(quoteService.getRandom());
    }

    /**
     * Получение 10 лучших цитат
     *
     * @return список лучших цитат
     */
    @GetMapping("/likes/top")
    public ResponseEntity<List<ViewQuoteModel>> getTopByLikes() {
        return ResponseEntity.ok(quoteService.getTopByLikes());
    }

    /**
     * Получение 10 худших цитат
     *
     * @return список худших цитат
     */
    @GetMapping("/dislikes/top")
    public ResponseEntity<List<ViewQuoteModel>> getTopByDislikes() {
        return ResponseEntity.ok(quoteService.getTopByDislikes());
    }

}

