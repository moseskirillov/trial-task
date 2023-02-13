package com.kameloon.trialtask.service.implementation;

import com.kameloon.trialtask.exception.QuoteNotFoundException;
import com.kameloon.trialtask.exception.ServerErrorException;
import com.kameloon.trialtask.repository.QuoteRepository;
import com.kameloon.trialtask.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final QuoteRepository quoteRepository;

    @Override
    public void like(Long id) {
        try {
            var quote = quoteRepository.findById(id);
            if (quote.isPresent()) {
                var quoteEntity = quote.get();
                quoteEntity.getVotes().setLikes(quoteEntity.getVotes().getLikes() + 1);
                quoteRepository.save(quoteEntity);
            } else {
                throw new QuoteNotFoundException(id.toString());
            }
        } catch (QuoteNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public void dislike(Long id) {
        try {
            var quote = quoteRepository.findById(id);
            if (quote.isPresent()) {
                var quoteEntity = quote.get();
                quoteEntity.getVotes().setDislikes(quoteEntity.getVotes().getDislikes() + 1);
                quoteRepository.save(quoteEntity);
            } else {
                throw new QuoteNotFoundException(id.toString());
            }
        } catch (QuoteNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServerErrorException(e.getMessage());
        }

    }
}
