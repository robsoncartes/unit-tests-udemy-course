package br.com.releasesolutions.services;

import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
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

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test() {

        // scenery
        LeaseService leaseService = new LeaseService();
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
        LeaseService leaseService = new LeaseService();
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        // Action
        Lease lease = leaseService.leaseMovie(user, movie);

        // Verifications
        error.checkThat(lease.getPrice(), is(equalTo(5.0)));
        error.checkThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
        error.checkThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));
    }

    @Test(expected = Exception.class)
    public void test_leaseMovieWithoutStock() throws Exception {

        LeaseService service = new LeaseService();
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 0, 5.0);

        // action
        service.leaseMovie(user, movie);
    }

    @Test
    public void test_leaseMovieWithoutStock_2() {

        LeaseService service = new LeaseService();
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 0, 5.0);

        // action
        try {
            service.leaseMovie(user, movie);
            fail("Should throw an exception.");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Movie unavailable."));
        }
    }

    @Test
    public void test_leaseMovieWithoutStock_3() throws Exception {

        LeaseService service = new LeaseService();
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 0, 5.0);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("Movie unavailable.");

        // action
        service.leaseMovie(user, movie);
    }
}
