package com.kameloon.trialtask.quote;

import com.kameloon.trialtask.entity.Quote;
import com.kameloon.trialtask.entity.User;
import com.kameloon.trialtask.exception.QuoteNotFoundException;
import com.kameloon.trialtask.exception.UserNotFoundException;
import com.kameloon.trialtask.mapper.QuoteMapper;
import com.kameloon.trialtask.model.request.CreateQuoteRequest;
import com.kameloon.trialtask.model.request.UpdateQuoteRequest;
import com.kameloon.trialtask.model.ViewQuoteModel;
import com.kameloon.trialtask.model.VoteModel;
import com.kameloon.trialtask.repository.QuoteRepository;
import com.kameloon.trialtask.repository.UserRepository;
import com.kameloon.trialtask.service.implementation.QuoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class QuoteTest {

    @InjectMocks
    private QuoteServiceImpl quoteService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private QuoteMapper quoteMapper;

    @Mock
    private QuoteRepository quoteRepository;

    @Test
    void testCreateQuote() {
        var createRequest = new CreateQuoteRequest(1L, "content");

        var user = new User();
        user.setId(1L);
        user.setQuotes(new ArrayList<>());

        var quote = new Quote();
        quote.setId(1L);
        quote.setContent("content");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(quoteMapper.toEntity(any(CreateQuoteRequest.class))).thenReturn(quote);
        when(quoteRepository.save(any(Quote.class))).thenReturn(quote);

        var result = quoteService.create(createRequest);
        assertEquals(result.id(), 1L);
    }

    @Test
    void testCreateWithNullUser() {
        var createRequest = new CreateQuoteRequest(1L, "content");

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> quoteService.create(createRequest))
                .isInstanceOf(UserNotFoundException.class);
    }


    @Test
    void testFindQuoteById() {
        var response = new ViewQuoteModel(1L, 1L, "content", LocalDateTime.now(), LocalDateTime.now(), new VoteModel(0, 0));

        var quote = new Quote();
        quote.setId(1L);
        quote.setContent("content");

        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));
        when(quoteMapper.toViewModel(any(Quote.class))).thenReturn(response);

        var result = quoteService.findById(1L);

        assertEquals(result.id(), 1L);
        assertEquals(result.userId(), 1L);
        assertEquals(result.content(), "content");
    }

    @Test
    void testFindQuoteByIdWhenQuoteNotFound() {
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.empty());
        var result = quoteService.findById(1L);
        assertNull(result);
    }

    @Test
    void testUpdateQuote() {
        var quote = new Quote();
        quote.setId(1L);
        quote.setContent("content");

        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));

        quoteService.update(1L, new UpdateQuoteRequest("updated content"));

        verify(quoteRepository).updateQuote(anyLong(), anyString(), any(LocalDateTime.class));
    }

    @Test
    void testUpdateNullQuote() {
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> quoteService.update(1L, new UpdateQuoteRequest("updated content")))
                .isInstanceOf(QuoteNotFoundException.class);
    }


    @Test
    void testGetRandomQuote() {
        var response = new ViewQuoteModel(1L, 1L, "content", LocalDateTime.now(), LocalDateTime.now(), new VoteModel(0, 0));

        var quote = new Quote();
        quote.setId(1L);
        quote.setContent("content");

        when(quoteRepository.getRandomQuote()).thenReturn(quote);
        when(quoteMapper.toViewModel(any(Quote.class))).thenReturn(response);

        var result = quoteService.getRandom();

        assertEquals(result.id(), 1L);
        assertEquals(result.userId(), 1L);
        assertEquals(result.content(), "content");
    }

    @Test
    void testDelete() {
        quoteService.deleteById(1L);
        verify(quoteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetTopByLikes() {
        var response = new ViewQuoteModel(1L, 1L, "content", LocalDateTime.now(), LocalDateTime.now(), new VoteModel(10, 0));
        var quotes = List.of(new Quote());

        when(quoteRepository.getTopByLikes()).thenReturn(quotes);
        when(quoteMapper.toViewModel(any(Quote.class))).thenReturn(response);

        var result = quoteService.getTopByLikes();

        assertEquals(result.get(0).id(), result.get(0).id());
        assertEquals(result.get(0).userId(), result.get(0).userId());
        assertEquals(result.get(0).dateOfCreation(), result.get(0).dateOfCreation());
        assertEquals(result.get(0).content(), result.get(0).content());
    }

    @Test
    void testGetTopByDislikes() {
        var response = new ViewQuoteModel(1L, 1L, "content", LocalDateTime.now(), LocalDateTime.now(), new VoteModel(0, 10));
        var quotes = List.of(new Quote());

        when(quoteRepository.getTopByLikes()).thenReturn(quotes);
        when(quoteMapper.toViewModel(any(Quote.class))).thenReturn(response);

        var result = quoteService.getTopByLikes();

        assertEquals(result.get(0).id(), result.get(0).id());
        assertEquals(result.get(0).userId(), result.get(0).userId());
        assertEquals(result.get(0).dateOfCreation(), result.get(0).dateOfCreation());
        assertEquals(result.get(0).content(), result.get(0).content());
    }

}
