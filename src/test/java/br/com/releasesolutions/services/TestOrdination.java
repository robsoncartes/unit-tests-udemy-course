package br.com.releasesolutions.services;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrdination {

    public static int counter = 0;

    @Test
    public void start() {
        counter = 1;
        assertEquals(1, counter);
    }

    @Test
    public void verify() {
        assertEquals(1, counter);
    }
}
