package com.example.demo.Utils;

import com.example.demo.Models.Type;
import com.example.demo.Utils.Result;
import com.example.demo.Utils.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTest {
    @Test
    void testResultConstructor() {
        Result actualResult = new Result(State.SUCCESS, 123L, Type.Doctor);
        actualResult.setId(123L);
        assertEquals(123L, actualResult.getId().longValue());
        assertEquals(actualResult.getState(), State.SUCCESS);
    }

    @Test
    void testStateTrue() {
        assertEquals((new Result(State.SUCCESS, 50L, Type.Doctor)).getState(), State.SUCCESS);
    }

    @Test
    void testStateFalse() {
        assertEquals((new Result(State.FAILURE, 50L, Type.Doctor)).getState(), State.FAILURE);
    }
}
