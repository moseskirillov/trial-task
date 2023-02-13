package com.kameloon.trialtask.user;

import com.kameloon.trialtask.entity.User;
import com.kameloon.trialtask.exception.UserAlreadyExistException;
import com.kameloon.trialtask.mapper.UserMapper;
import com.kameloon.trialtask.model.request.CreateUserRequest;
import com.kameloon.trialtask.repository.UserRepository;
import com.kameloon.trialtask.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void shouldCreateUser() {
        var request = new CreateUserRequest("John Doe", "johndoe@example.com", "password");
        var user = new User();
        when(userRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(userMapper.toEntity(request)).thenReturn(user);

        userService.create(request);

        verify(userRepository).save(user);
    }

    @Test
    void create_userAlreadyExists_throwsUserAlreadyExistException() {
        var user = new User();
        user.setEmail("johndoe@example.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        var request = new CreateUserRequest("John Doe", "johndoe@example.com", "password");
        assertThatThrownBy(() -> userService.create(request))
                .isInstanceOf(UserAlreadyExistException.class)
                .hasMessage("johndoe@example.com");
    }

}
