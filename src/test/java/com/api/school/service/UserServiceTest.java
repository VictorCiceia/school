package com.api.school.service;

import com.api.school.dto.UserDto;
import com.api.school.entity.UserEntity;
import com.api.school.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void buscarTodosDebeFuncionar(){
        // Preparacion
        UserEntity user1 = new UserEntity();
        user1.setId("1");
        user1.setName("John");
        user1.setEmail("john@example.com");

        UserEntity user2 = new UserEntity();
        user2.setId("2");
        user2.setName("Jane");
        user2.setEmail("jane@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Ejecucion
        List<UserDto> users = userService.getAll();

        // Validacion
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getName());
        assertEquals("Jane", users.get(1).getName());
    }

    @Test
    public void buscarPorIdDebeFuncionar(){
        // Preparacion
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("John");
        user.setEmail("john@example.com");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        // Ejecucion
        UserDto userDto = userService.getById("1");

        // Validacion
        assert userDto.getId().equals("1");
        assert userDto.getName().equals("John");
        assert userDto.getEmail().equals("john@example.com");
    }

    @Test
    public void guardarDebeFuncionar(){
        // Preparacion
        UserDto userDto = new UserDto(null, "John", "john@example.com");
        UserEntity user = new UserEntity();
        user.setName("John");
        user.setEmail("john@example.com");

        UserEntity savedUser = new UserEntity();
        savedUser.setId("1");
        savedUser.setName("John");
        savedUser.setEmail("john@example.com");

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        // Ejecucion
        UserDto savedUserDto = userService.save(userDto);

        // Validacion
        assertNotNull(savedUserDto);
        assertEquals("1", savedUserDto.getId());
        assertEquals("John", savedUserDto.getName());
    }

    @Test
    public void actualizarDebeFuncionar(){
        // Preparacion
        UserDto userDto = new UserDto(null, "John", "john@example.com");

        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("John");
        user.setEmail("john@example.com");

        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        // Ejecucion
        UserDto updatedUser = userService.update(userDto, "1");

        // Validacion
        assertNotNull(updatedUser);
        assertEquals("1", updatedUser.getId());
        assertEquals("John", updatedUser.getName());
        assertEquals("john@example.com", updatedUser.getEmail());

    }

    @Test
    public void eliminarDebeFuncionar(){
        // Preparacion
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("John");
        user.setEmail("john@example.com");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        // Ejecucion
        userService.delete("1");

        // Validacion
        verify(userRepository, times(1)).delete(user);
    }

}
