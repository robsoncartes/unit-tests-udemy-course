package br.com.releasesolutions.services;

import br.com.releasesolutions.exceptions.MovieWithoutStockException;
import br.com.releasesolutions.exceptions.RentalException;
import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static br.com.releasesolutions.utils.DateUtils.getDateWithDaysDifference;
import static br.com.releasesolutions.utils.DateUtils.isSameDate;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class LeaseServiceTest {

    private LeaseService leaseService;

    // Counter definition
    private static int counter = 0;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        System.out.println("Before");
        leaseService = new LeaseService();
        // increment
        // counter printing
        counter++;
        System.out.println(counter);
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @BeforeClass
    public static void setupClass() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("AfterClass");
    }

    @Test
    public void test() {

        // scenery
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        // action
        Lease lease;

        try {
            lease = leaseService.leaseMovie(user, movie);

            // verification
            assertThat(lease.getPrice(), is(equalTo(5.0)));
            assertThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
            assertThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));

        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception.");
        }
    }

    @Test
    public void test_testWithRule() throws Exception {

        // Scenery
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        // Action
        Lease lease = leaseService.leaseMovie(user, movie);

        // Verifications
        error.checkThat(lease.getPrice(), is(equalTo(5.0)));
        error.checkThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
        error.checkThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));
    }

    @Test(expected = MovieWithoutStockException.class)
    public void test_leaseMovieWithoutStock() throws Exception {

        // Scenery
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 0, 5.0);

        // action
        leaseService.leaseMovie(user, movie);
    }

    @Test
    public void test_leaseMovieWithoutStock_3() throws Exception {

        // Scenery
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 0, 5.0);

        expectedException.expect(Exception.class);

        // action
        leaseService.leaseMovie(user, movie);
    }

    @Test
    public void test_leaseMovieWithUserEqualNull() throws MovieWithoutStockException {

        // best form of implementation
        // Scenery
        Movie movie = new Movie("Movie 1", 1, 5.0);

        // action
        try {
            leaseService.leaseMovie(null, movie);
            fail();

        } catch (RentalException e) {
            assertThat(e.getMessage(), is("User null."));
        }
    }

    @Test
    public void test_leaseMovieWithMovieEqualNull() throws RentalException, MovieWithoutStockException {

        // Scenery
        User user = new User("User 1");
        expectedException.expect(RentalException.class);
        expectedException.expectMessage("Movie null.");

        // Actions
        leaseService.leaseMovie(user, null);
    }
}
