package com.kameloon.trialtask.mapper;

import com.kameloon.trialtask.entity.Quote;
import com.kameloon.trialtask.model.QuoteModel;
import com.kameloon.trialtask.model.ViewQuoteModel;
import com.kameloon.trialtask.model.request.CreateQuoteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Маппер цитаты, проставляет дату создания / обновления
 */
@Mapper(componentModel = "spring", uses = VotesMapper.class)
public interface QuoteMapper {
    QuoteModel toModel(Quote quote);

    @Mappings({
            @Mapping(target = "dateOfCreation", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "dateOfUpdate", expression = "java(java.time.LocalDateTime.now())"),
    })
    Quote toEntity(CreateQuoteRequest request);

    @Mapping(target = "userId", expression = "java(quote.getUser().getId())")
    ViewQuoteModel toViewModel(Quote quote);
}
