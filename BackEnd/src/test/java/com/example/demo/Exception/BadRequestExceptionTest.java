package com.example.demo.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class BadRequestExceptionTest {
    @Test
    void testConstructor() {
        BadRequestException actualBadRequestException = new BadRequestException("This is a message");
        assertNull(actualBadRequestException.getCause());
        assertEquals(0, actualBadRequestException.getSuppressed().length);
        assertEquals("This is a message", actualBadRequestException.getMessage());
        assertEquals("This is a message", actualBadRequestException.getLocalizedMessage());
    }
}
