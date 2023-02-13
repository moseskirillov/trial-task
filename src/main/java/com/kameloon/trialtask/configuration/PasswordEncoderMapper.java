package com.kameloon.trialtask.configuration;

import com.kameloon.trialtask.annotation.EncodedMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Компонент для добавления в маппер пользователя шифрования пароля
 */
@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {

    private final PasswordEncoder passwordEncoder;

    @EncodedMapping
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

}
