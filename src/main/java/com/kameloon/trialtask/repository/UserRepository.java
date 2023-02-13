package com.kameloon.trialtask.repository;

import com.kameloon.trialtask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с пользователем
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Метод поиска пользователя по адресу электронной почты
     *
     * @param email почта
     * @return созданную сущность
     */
    Optional<User> findByEmail(String email);
}