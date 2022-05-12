package br.com.releasesolutions.services;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculatorMockTest {

    @Mock
    private Calculator calculatorMock;

    @Spy
    private Calculator calculatorSpy;

    @Rule //initMocks
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {

    }

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
    }

    @Test
    public void test_shouldDemonstrateTheDifferenceBetweenMockAndSpy() {

        when(calculatorMock.sum(1, 2)).thenCallRealMethod();
        when(calculatorSpy.sum(1, 3)).thenReturn(8);

        System.out.println("Mock: " + calculatorMock.sum(1, 2));
        System.out.println("Spy: " + calculatorSpy.sum(1, 2));

        System.out.println("Mock");
        calculatorMock.printSomething();

        System.out.println("Spy");
        calculatorSpy.printSomething();

        doNothing().when(calculatorSpy).printSomething();
        calculatorSpy.printSomething();

        doReturn(5).when(calculatorSpy).sum(1, 2);
    }
}
