package com.example.java.test.junior.developer.user;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.mapper.UserMapper;
import com.example.java.test.junior.developer.model.User;

public class UserMapperJunitTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    public void toEntity_ValidDto_ReturnsEntity() {
        UserDto userDto = new UserDto(1, "Tom", "Disney", "tom@email");

        User result = userMapper.toEntity(userDto);

        assertEquals(userDto.getName(), result.getName());
        assertEquals(userDto.getSurname(), result.getSurname());
        assertEquals(userDto.getEmail(), result.getEmail());
    }

    @Test
    public void toDto_ValidEntity_ReturnsDto() {
        User user = User.builder()
                .id(1)
                .name("Tom")
                .surname("Disney")
                .email("tom@email")
                .build();

        UserDto result = userMapper.toDto(user);

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getSurname(), result.getSurname());
        assertEquals(user.getEmail(), result.getEmail());
    }
}
