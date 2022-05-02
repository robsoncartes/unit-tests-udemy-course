package br.com.releasesolutions.services;

import br.com.releasesolutions.exceptions.MovieWithoutStockException;
import br.com.releasesolutions.exceptions.RentalException;
import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.List;

import static br.com.releasesolutions.utils.DateUtils.getDateWithDaysDifference;
import static br.com.releasesolutions.utils.DateUtils.isSameDate;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class LeaseServiceTest {

    private LeaseService leaseService;
    private User user;
    private List<Movie> movies;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {

        // Common scenery
        leaseService = new LeaseService();
        user = new User("User 1");
        movies = List.of(new Movie("Movie 1", 1, 5.0), new Movie("Movie 2", 2, 4.0));
    }

    @Test
    public void test() {

        // action

        try {
            Lease lease = leaseService.leaseMovie(user, movies);

            // verification
            assertThat(lease.getPrice(), is(equalTo(9.0)));
            assertThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
            assertThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));

        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception.");
        }
    }

    @Test
    public void test_testWithRule() throws Exception {

        // Action
        Lease lease = leaseService.leaseMovie(user, movies);

        // Verifications
        error.checkThat(lease.getPrice(), is(equalTo(9.0)));
        error.checkThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
        error.checkThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));
    }

    @Test(expected = MovieWithoutStockException.class)
    public void test_leaseMovieWithoutStock() throws Exception {

        // Scenery
        movies.get(0).setStock(0);

        // action
        leaseService.leaseMovie(user, movies);
    }

    @Test
    public void test_leaseMovieWithoutStock_3() throws Exception {

        // Scenery
        movies.get(0).setStock(0);
        expectedException.expect(Exception.class);

        // action
        leaseService.leaseMovie(user, movies);
    }

    @Test
    public void test_leaseMovieWithUserEqualNull() throws MovieWithoutStockException {

        // best form of implementation

        // action
        try {
            leaseService.leaseMovie(null, movies);
            fail();

        } catch (RentalException e) {
            assertThat(e.getMessage(), is("User null."));
        }
    }

    @Test
    public void test_leaseMovieWithMovieEqualNull() throws RentalException, MovieWithoutStockException {

        // Scenery
        expectedException.expect(RentalException.class);
        expectedException.expectMessage("Movie null.");

        // Actions
        leaseService.leaseMovie(user, null);
    }
}
