package com.kameloon.trialtask.repository;

import com.kameloon.trialtask.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Репозиторий работы с цитатами
 */
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Modifying
    @Query("UPDATE Quote q SET q.content = :content, q.dateOfUpdate = :now WHERE q.id = :id")
    void updateQuote(Long id, String content, LocalDateTime now);

    @Modifying
    @Query("DELETE FROM Quote q WHERE q.id = :id")
    void deleteById(Long id);

    @Query("SELECT q FROM Quote q ORDER BY RAND() LIMIT 1")
    Quote getRandomQuote();

    @Query("SELECT q FROM Quote q JOIN q.votes v ORDER BY v.likes DESC LIMIT 10")
    List<Quote> getTopByLikes();

    @Query("SELECT q FROM Quote q JOIN q.votes v ORDER BY v.dislikes DESC LIMIT 10")
    List<Quote> getTopByDislikes();

}
