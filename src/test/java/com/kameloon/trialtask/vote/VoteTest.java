package com.kameloon.trialtask.vote;

import com.kameloon.trialtask.entity.Quote;
import com.kameloon.trialtask.entity.Vote;
import com.kameloon.trialtask.repository.QuoteRepository;
import com.kameloon.trialtask.service.implementation.VoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VoteTest {

    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private QuoteRepository quoteRepository;

    @Test
    void testLike() {
        var quote = new Quote();
        quote.setId(1L);
        quote.setContent("content");
        quote.setVotes(new Vote());

        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));

        voteService.like(1L);
    }

    @Test
    void testDislike() {
        var quote = new Quote();
        quote.setId(1L);
        quote.setContent("content");
        quote.setVotes(new Vote());

        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));

        voteService.dislike(1L);
    }
}