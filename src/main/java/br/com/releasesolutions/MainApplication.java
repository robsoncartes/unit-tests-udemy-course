package br.com.releasesolutions;

import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import br.com.releasesolutions.services.LeaseService;
import br.com.releasesolutions.utils.DateUtils;

import java.util.Date;

public class MainApplication {

    public static void main(String[] args) {

        // scenery
        LeaseService leaseService = new LeaseService();
        User user = new User("User 1");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        // action
        Lease lease = leaseService.leaseMovie(user, movie);

        // verification
        System.out.println(lease.getPrice() == 5.0);
        System.out.println(DateUtils.isSameDate(lease.getLeaseDate(), new Date()));
        System.out.println(DateUtils.isSameDate(lease.getDeliveryDate(), DateUtils.getDateWithDaysDifference(1)));
    }
}
