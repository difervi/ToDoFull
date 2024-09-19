package com.todo.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("jhon-doe@example.com");
        user.setPassword("password");

        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("password", user.getPassword());
    }

}
