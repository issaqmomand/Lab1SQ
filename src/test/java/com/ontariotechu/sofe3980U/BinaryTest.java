package com.ontariotechu.sofe3980U;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTest {

    @Test
    void testAddition() {
        Binary b1 = new Binary("1010");
        Binary b2 = new Binary("110");
        Binary result = Binary.add(b1, b2);
        assertEquals("10000", result.getValue());
    }

    @Test
    void testOr() {
        Binary b1 = new Binary("1010");
        Binary b2 = new Binary("1100");
        Binary result = Binary.or(b1, b2);
        assertEquals("1110", result.getValue());
    }

    @Test
    void testAnd() {
        Binary b1 = new Binary("1010");
        Binary b2 = new Binary("1100");
        Binary result = Binary.and(b1, b2);
        assertEquals("1000", result.getValue());
    }

    @Test
    void testMultiply() {
        Binary b1 = new Binary("101");
        Binary b2 = new Binary("10");
        Binary result = Binary.multiply(b1, b2);
        assertEquals("1010", result.getValue());
    }
}
