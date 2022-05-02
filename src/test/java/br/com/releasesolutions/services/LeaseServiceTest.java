package br.com.releasesolutions.services;

import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Date;

import static br.com.releasesolutions.utils.DateUtils.getDateWithDaysDifference;
import static br.com.releasesolutions.utils.DateUtils.isSameDate;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LeaseServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void test() {

        // scenery
        LeaseService leaseService = new LeaseService();
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        // action
        Lease lease = leaseService.leaseMovie(user, movie);

        // verification
        assertThat(lease.getPrice(), is(equalTo(5.0)));
        assertThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
        assertThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));
    }

    @Test
    public void test_testWithRule() {

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
}
