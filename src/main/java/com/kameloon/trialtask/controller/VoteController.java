package com.kameloon.trialtask.controller;

import com.kameloon.trialtask.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для добавления лайков и дизлайков
 */
@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    /**
     * Добавить лайк цитате по id
     *
     * @param id id цитаты
     * @return HTTP 200
     */
    @GetMapping("like/{id}")
    public ResponseEntity<Void> like(@PathVariable Long id) {
        voteService.like(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Добавить дизлайк цитате по id
     *
     * @param id id цитаты
     * @return HTTP 200
     */
    @GetMapping("dislike/{id}")
    public ResponseEntity<Void> dislike(@PathVariable Long id) {
        voteService.dislike(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
