package br.com.releasesolutions.services;

import br.com.releasesolutions.exceptions.DivideByZeroException;
import br.com.releasesolutions.runners.ParallelRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(ParallelRunner.class)
public class CalculatorTest {

    private Calculator calculator;
    private int a;
    private int b;

    @Before
    public void setup() {

        // Common scenery
        a = 5;
        b = 3;
        calculator = new Calculator();
        System.out.println("Starting...");
    }

    @After
    public void tearDown() {

        System.out.println("Finishing...");
    }

    @Test
    public void test_shouldSumTwoValues() {

        // Action
        int result = calculator.sum(a, b);

        // Verification
        assertEquals(8, result);
    }

    @Test
    public void test_shouldDeductTwoValues() {

        // Action
        int result = calculator.deduct(a, b);

        // Verification
        assertEquals(2, result);
    }

    @Test
    public void test_shouldDivideTwoValues() throws DivideByZeroException {

        // Action
        int result = calculator.divide(a, b);

        // Verification
        assertEquals(1, result);
    }

    @Test(expected = DivideByZeroException.class)
    public void test_shouldThrowAnExceptionWhenDenominatorIsZero() throws DivideByZeroException {

        // Scenery
        int denominator = 0;

        // Action
        calculator.divide(a, denominator);
    }

    @Test
    public void test_shouldDivideNumeratorByDenominator() {

        String a = "6";
        String b = "3";

        // Action
        int result = calculator.divide(a, b);

        // Verification
        assertEquals(2, result);
    }
}
