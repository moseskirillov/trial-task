package com.kameloon.trialtask.service.implementation;

import com.kameloon.trialtask.exception.ServerErrorException;
import com.kameloon.trialtask.exception.UserAlreadyExistException;
import com.kameloon.trialtask.mapper.UserMapper;
import com.kameloon.trialtask.model.request.CreateUserRequest;
import com.kameloon.trialtask.model.response.UserResponse;
import com.kameloon.trialtask.repository.UserRepository;
import com.kameloon.trialtask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private static final String CREATE_USER_REQUEST_INFO = "Поступил запрос на создание пользователя с почтой: {}";
    private static final String CREATE_USER_SUCCESS_INFO = "Пользователь {} успешно сохранен";

    @Override
    @Transactional
    public void create(CreateUserRequest request) {
        try {
            log.info(CREATE_USER_REQUEST_INFO, request.email());
            var userExist = userRepository.findByEmail(request.email());
            if (userExist.isPresent()) {
                throw new UserAlreadyExistException(request.email());
            }
            var user = userMapper.toEntity(request);
            userRepository.save(user);
            log.info(CREATE_USER_SUCCESS_INFO, user.getEmail());
        } catch (UserAlreadyExistException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<UserResponse> findAll() {
        try {
            return userRepository.findAll()
                    .stream()
                    .map(userMapper::toModel)
                    .toList();
        } catch (RuntimeException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
