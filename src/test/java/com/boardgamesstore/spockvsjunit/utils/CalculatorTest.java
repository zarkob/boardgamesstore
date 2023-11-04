package com.boardgamesstore.spockvsjunit.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void add() {
        int result = calculator.add(2, 3);
        assertEquals(5, result, "Sum of 2 and 3 should be 5");
    }

    @Test
    void subtract() {
        int result = calculator.subtract(5, 2);
        assertEquals(3, result, "Difference of 5 and 2 should be 3");
    }

    @Test
    void multiply() {
        int result = calculator.multiply(3, 4);
        assertEquals(12, result, "Product of 3 and 4 should be 12");
    }

    @Test
    void divide() {
        double result = calculator.divide(6, 3);
        assertEquals(2.0d, result, "Quotient of 6 and 3 should be 2.0");
    }

    @Test
    void modulo() {
        int result = calculator.modulo(10, 3);
        assertEquals(1, result, "Remainder of 10 divided by 3 should be 1");
    }

    @Test
    void power() {
        double result = calculator.power(3, 4);
        assertEquals(81.0d, result, "Result of 3 to the power of 4 should be 81");
    }

    @Test
    void negate() {
        int result = calculator.negate(-5);
        assertEquals(5, result, "Negation of -5 should be 5");
    }

    @Test
    void abs() {
        int result = calculator.abs(-5);
        assertEquals(5, result, "Absolute value of -5 should be 5");
    }

    @Test
    void sqrt() {
        double result = calculator.sqrt(4);
        assertEquals(2.0d, result, "Square root of 4 should be 2.0");
    }

    @Test
    void floor() {
        double result = calculator.floor(4.5);
        assertEquals(4.0d, result, "Floor of 4.5 should be 4.0");
    }

    @Test
    void ceil() {
        double result = calculator.ceil(4.5);
        assertEquals(5.0d, result, "Ceiling of 4.5 should be 5.0");
    }

    @Test
    void round() {
        double result = calculator.round(4.5);
        assertEquals(5.0d, result, "Rounding of 4.5 to the nearest integer should be 5.0");
    }


    @Test
    void random() {
        double result = calculator.random(0, 1);
        assertTrue(result >= 0.0d && result <= 1.0d, "Random number should be between 0 and 1");
    }

}
