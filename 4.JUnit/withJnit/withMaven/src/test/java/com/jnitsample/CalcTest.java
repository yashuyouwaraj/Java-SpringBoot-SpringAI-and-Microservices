package com.jnitsample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalcTest {
    @Test
    public void test() {
        Calc c = new Calc();
        int result = c.divide(10, 2);
        int expected = 5;
        assertEquals(expected, result);
    }

    @Test
    void testString(){
        String str="Junit";
        assertTrue(str.equals("Junit"));
    }
}
