package br.com.releasesolutions.services;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

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

    @Test
    public void test_argumentCaptor() {

        Calculator calculator = mock(Calculator.class);

        ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
        when(calculator.sum(argCapt.capture(), argCapt.capture())).thenReturn(5);

        assertEquals(5, calculator.sum(-199, 42));
        System.out.println(argCapt.getAllValues());
    }
}
