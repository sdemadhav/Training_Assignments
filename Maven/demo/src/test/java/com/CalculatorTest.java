package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(4, calculator.add(2, 2), "The sum of 2 and 2 is not resulting in 4");
    }

    @Test
    public void testSub() {
        Calculator calculator = new Calculator();
        assertEquals(0, calculator.sub(2, 2), "The difference of 2 and 2 is not resulting in 0");
    }
}
