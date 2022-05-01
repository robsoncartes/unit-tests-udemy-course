package br.com.releasesolutions.services;

import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AssertTest {

    @Test
    public void test_assertTrue() {

        assertTrue(true);
        assertTrue("Ball".equalsIgnoreCase("ball"));
        assertTrue("Ball".endsWith("ll"));
    }

    @Test
    public void test_assertNotFalse() {

        /*
            For studies purposes only:
            Avoid using the negation of logical operators.
            Instead, use: assertTrue(true)
         */

        assertTrue(!false);
    }

    @Test
    public void test_assertFalse() {

        assertFalse(false);
        assertFalse("ball".startsWith("bo"));
    }

    @Test
    public void test_assertNotTrue() {

        /*
            For studies purposes only:
            Avoid using the negation of logical operators.
            Instead, use: assertFalse(false);
         */

        assertFalse(!true);
    }

    @Test
    public void test_assertEquals() {

        int i = 5;
        Integer j = 5;

        double k = 5.0;
        Double l = 5.0;

        User user1 = new User("User 1");
        User user2 = new User("User 1");
        User user3 = user2;

        assertEquals(1, 1);
        assertEquals(Math.round(5.6), 6);
        assertEquals(i, j.intValue());
        assertEquals(Integer.valueOf(i), j);
        assertEquals(Double.valueOf(k), l);
        assertEquals("Ball".toLowerCase(), "ball");

        assertEquals(user1, user2);
        assertEquals("Optional message: values are not equals.", user2, user3);
    }

    @Test
    public void test_assertNotEquals() {

        User user1 = new User("User 1");
        User user2 = new User("User 2");
        User user3 = user2;

        assertNotEquals(1, 2);
        assertNotEquals(Math.PI, 3.14);
        assertNotEquals(Math.floor(5.5), 5);
        assertNotEquals(user1, user2);
        assertNotEquals("Optional message: values are equals.", user1, user3);
    }

    @Test
    public void test_assertEqualsWithDeltaParameter() {

        double k = 5.0;
        Double l = 5.0;

        assertEquals(1.1, 1.101, 0.001);
        assertEquals(Math.PI, 3.14, 0.01);
        assertEquals(Math.floor(5.5), 5.0, 0.0);
        assertEquals(Math.round(5.6), 6.0, 0.0);

        assertEquals("Optional message: values are not equals.", k, l.doubleValue(), 0);

    }

    @Test
    public void test_assertNotEqualsWithDeltaParameter() {

        assertNotEquals("Values are equals.", 1.01, 1.02);

    }

    @Test
    public void test_assertSame() {

        User user1 = new User("User 1");
        User user2 = user1;
        User user3 = user2;

        assertSame(user1, user2);
        assertSame("Optional message: objects are of different instances.", user2, user3);
    }

    @Test
    public void test_assertNotSame() {

        User user1 = new User("User 1");
        User user2 = new User("User 1");
        User user3 = user2;

        assertNotSame(user1, user2);
        assertNotSame("Optional message: objects are of the same instance.", user1, user3);
    }

    @Test
    public void test_assertNull() {

        User user1 = new User(null);

        assertNull(null);
        assertNull("Optional message: name is not null.", user1.getName());
    }

    @Test
    public void test_assertNotNull() {

        User user1 = new User();
        User user2 = new User("User 2");

        assertNotNull(user1);
        assertNotNull("Optional message: name is null.", user2.getName());
    }

    @Test
    public void test_assertArrayEquals() {

        int[] numbers = {1, 2, 3};
        int[] copy = {1, 2, 3};

        assertArrayEquals(numbers, copy);
    }

    @Test
    public void test_assertThat() {

        User user1 = new User("User 1");
        Lease lease1 = new Lease();

        lease1.setPrice(5.0);
        Double i = 5.0;
        double j = 5.0;

        assertThat(user1.getName(), is("User 1"));
        assertThat(lease1.getPrice(), is(equalTo(5.0)));
        assertThat(i, is(equalTo(j)));
    }
}
