package com.example.unit_testing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    CalculatorModel calculatorModel = new CalculatorModel();

    @Test
    public void checkAddition() {
        assertEquals(4, CalculatorModel.add(2,2));
    }

    @Test
    public void checkSubtraction() {
        assertEquals(4, CalculatorModel.subtract(6,2));
    }

    @Test
    public void checkSubtractionAgain() {
        int expected = 4;
        int actual = CalculatorModel.subtract(6,2);
        assertEquals("Subtraction Failed", actual, expected);
    }

    @Test
    public void checkDivision() {
        assertEquals("INFINITY", CalculatorModel.divide(4,0));
        assertEquals("NOT DEFINED", CalculatorModel.divide(0,0));
        assertEquals("2", CalculatorModel.divide(4,2));
    }

    @Test
    public void checkMultiply() {
        assertEquals(10, CalculatorModel.multiply(2,5));
    }

    @Test
    public void checkMultiplyAnInt() {
        assertEquals(10, calculatorModel.multiplyAnInt(2,5));
    }

}
