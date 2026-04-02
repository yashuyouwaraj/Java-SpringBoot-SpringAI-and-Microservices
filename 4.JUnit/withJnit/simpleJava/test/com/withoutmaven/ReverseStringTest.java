package com.withoutmaven;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReverseStringTest {

    @Test
    public void shouldReverseRegularString() {
        ReverseString rs = new ReverseString();
        String result = rs.reverseString("Hello");
        String expected = "olleH";
        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnEmptyForEmptyInput() {
        ReverseString rs = new ReverseString();
        String result = rs.reverseString("");
        assertEquals("", result);
    }

    @Test
    public void shouldReturnNullForNullInput() {
        ReverseString rs = new ReverseString();
        String result = rs.reverseString(null);
        assertNull(result);
    }

}
