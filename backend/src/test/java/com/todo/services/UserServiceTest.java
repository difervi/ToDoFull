/*package com.todo.services;

import com.todo.mapper.UserMapper;
import com.todo.dtos.UserCreateDto;
import com.todo.dtos.UserDto;
import com.todo.entities.User;
import com.todo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUsername("User 1");
        userCreateDto.setEmail("difervif@mail.com");
        userCreateDto.setPassword("password");

        User user = new User();
        user.setId(1L);
        user.setName("User 1");
        user.setEmail("difervif@mail.com");
        user.setPassword("password");

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("User 1");
        userDto.setEmail("difervif@mail.com");

        when(userMapper.toUser(userCreateDto)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserDto(user)).thenReturn(userDto);

        UserDto createdUser = userService.createUser(userCreateDto);

        assertEquals(1L, createdUser.getId());
        assertEquals("User 1", createdUser.getUsername());
        assertEquals("difervif@mail.com", createdUser.getEmail());
    }
}
*/
