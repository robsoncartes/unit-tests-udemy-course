package br.com.releasesolutions.services;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculatorMockTest {

    @Test
    public void test() {

        Calculator calculator = mock(Calculator.class);
        when(calculator.sum(eq(1), anyInt())).thenReturn(5);

        assertEquals(5, calculator.sum(1, 3));
    }
}
