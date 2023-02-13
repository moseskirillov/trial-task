package com.kameloon.trialtask.service.implementation;

import com.kameloon.trialtask.entity.Vote;
import com.kameloon.trialtask.exception.QuoteNotFoundException;
import com.kameloon.trialtask.exception.ServerErrorException;
import com.kameloon.trialtask.exception.UserNotFoundException;
import com.kameloon.trialtask.mapper.QuoteMapper;
import com.kameloon.trialtask.model.request.CreateQuoteRequest;
import com.kameloon.trialtask.model.request.CreateQuoteResponse;
import com.kameloon.trialtask.model.request.UpdateQuoteRequest;
import com.kameloon.trialtask.model.ViewQuoteModel;
import com.kameloon.trialtask.repository.QuoteRepository;
import com.kameloon.trialtask.repository.UserRepository;
import com.kameloon.trialtask.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteMapper quoteMapper;
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;

    @Override
    @Transactional
    public CreateQuoteResponse create(CreateQuoteRequest request) {
        try {
            var user = userRepository.findById(request.userId());
            if (user.isPresent()) {
                var quote = quoteMapper.toEntity(request);
                quote.setVotes(new Vote());
                quote.setUser(user.get());
                var savedQuote = quoteRepository.save(quote);
                user.get().getQuotes().add(quote);
                userRepository.save(user.get());
                return new CreateQuoteResponse(savedQuote.getId());
            } else {
                throw new UserNotFoundException(request.userId());
            }
        } catch (UserNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public ViewQuoteModel findById(Long id) {
        try {
            var quote = quoteRepository.findById(id);
            return quoteMapper.toViewModel(quote.orElse(null));
        } catch (ServerErrorException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Long id, UpdateQuoteRequest request) {
        try {
            var quote = quoteRepository.findById(id);
            if (quote.isPresent()) {
                quoteRepository.updateQuote(id, request.content(), LocalDateTime.now());
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
    @Transactional
    public void deleteById(Long id) {
        try {
            quoteRepository.deleteById(id);
        } catch (ServerErrorException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public ViewQuoteModel getRandom() {
        try {
            return quoteMapper.toViewModel(quoteRepository.getRandomQuote());
        } catch (ServerErrorException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public List<ViewQuoteModel> getTopByLikes() {
        try {
            return quoteRepository
                    .getTopByLikes()
                    .stream()
                    .map(quoteMapper::toViewModel)
                    .toList();
        } catch (ServerErrorException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public List<ViewQuoteModel> getTopByDislikes() {
        try {
            return quoteRepository
                    .getTopByDislikes()
                    .stream()
                    .map(quoteMapper::toViewModel)
                    .toList();
        } catch (ServerErrorException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
