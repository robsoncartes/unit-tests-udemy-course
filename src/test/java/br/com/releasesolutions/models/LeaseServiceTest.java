package br.com.releasesolutions.models;

import br.com.releasesolutions.services.LeaseService;
import br.com.releasesolutions.utils.DateUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
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
        assertTrue(DateUtils.isSameDate(lease.getLeaseDate(), new Date()));
        assertTrue(DateUtils.isSameDate(lease.getDeliveryDate(), DateUtils.getDateWithDaysDifference(1)));
    }
}
