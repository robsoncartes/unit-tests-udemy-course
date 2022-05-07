package br.com.releasesolutions.services;

import br.com.releasesolutions.exceptions.DivideByZeroException;

public class Calculator {

    public int sum(int a, int b) {

        return a + b;
    }

    public int deduct(int a, int b) {

        return a - b;
    }

    public int divide(int a, int b) throws DivideByZeroException {

        if (b == 0)
            throw new DivideByZeroException();
        return a / b;
    }

    public int divide(String a, String b) {

        return Integer.valueOf(a) / Integer.valueOf(b);
    }
}
