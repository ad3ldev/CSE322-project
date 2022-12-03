package com.example.AnotherDemo.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTest {
    @Test
    void testResultConstructor() {
        Result actualResult = new Result(true, 123L, 1);
        actualResult.setId(123L);
        actualResult.setState(true);
        assertEquals(123L, actualResult.getId().longValue());
        assertTrue(actualResult.isState());
    }

    @Test
    void testStateTrue() {
        assertTrue((new Result(true, 50L, 10)).isState());
    }

    @Test
    void testStateFalse() {
        assertFalse((new Result(false, 50L, 10)).isState());
    }
}
