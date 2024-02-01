package com.confra.api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomStringTest {

    @Test
    void shouldGenerateRandomString() {
        int lenght = 10;
        String randomString = RandomString.generateRandomString(lenght);
        String anotherRandomString = RandomString.generateRandomString(lenght);

        assertEquals(lenght, randomString.length());
        assertEquals(lenght, anotherRandomString.length());

        assertNotEquals(randomString, anotherRandomString);
    }
}