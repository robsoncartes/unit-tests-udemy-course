package br.com.releasesolutions.services;

import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import br.com.releasesolutions.utils.DateUtils;
import org.junit.Test;

import java.util.Date;

import static br.com.releasesolutions.utils.DateUtils.getDateWithDaysDifference;
import static br.com.releasesolutions.utils.DateUtils.isSameDate;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LeaseServiceTest {

    @Test
    public void test() {

        // scenery
        LeaseService leaseService = new LeaseService();
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        // action
        Lease lease = leaseService.leaseMovie(user, movie);

        // verification
        assertEquals(5.0, lease.getPrice(), 0.0);
        assertThat(lease.getPrice(), is(equalTo(5.0)));
        assertThat(lease.getPrice(), is(not(6.0)));
        assertTrue(isSameDate(lease.getLeaseDate(), new Date()));
        assertTrue(isSameDate(lease.getDeliveryDate(), DateUtils.getDateWithDaysDifference(1)));
        assertThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
        assertThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));
    }
}
