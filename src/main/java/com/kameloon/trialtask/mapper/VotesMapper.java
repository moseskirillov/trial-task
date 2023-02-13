package com.kameloon.trialtask.mapper;

import com.kameloon.trialtask.entity.Vote;
import com.kameloon.trialtask.model.VoteModel;
import org.mapstruct.Mapper;

/**
 * Маппер сущности голосов
 */
@Mapper(componentModel = "spring")
public interface VotesMapper {
    VoteModel toModel(Vote vote);
}
