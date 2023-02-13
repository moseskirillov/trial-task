package com.kameloon.trialtask.mapper;

import com.kameloon.trialtask.annotation.EncodedMapping;
import com.kameloon.trialtask.configuration.PasswordEncoderMapper;
import com.kameloon.trialtask.entity.User;
import com.kameloon.trialtask.model.request.CreateUserRequest;
import com.kameloon.trialtask.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Маппер сушности пользователя в модель и наоборот
 * При создании автоматически проставляет дату создания
 * А также шифрует пароль
 */
@Mapper(componentModel = "spring", uses = {
        PasswordEncoderMapper.class,
        QuoteMapper.class
})
public interface UserMapper {
    @Mappings({
            @Mapping(target = "dateOfCreation", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    })
    User toEntity(CreateUserRequest request);

    UserResponse toModel(User user);
}