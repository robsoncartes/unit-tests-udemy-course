package br.com.releasesolutions.services;

import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;

import java.util.Date;

import static br.com.releasesolutions.utils.DateUtils.addDays;

public class LeaseService {

    public Lease leaseMovie(User user, Movie movie) {

        Lease lease = new Lease();
        lease.setMovie(movie);
        lease.setUser(user);
        lease.setLeaseDate(new Date());
        lease.setPrice(movie.getLeasePrice());

        Date deliveryDate = new Date();
        deliveryDate = addDays(deliveryDate, 1);
        lease.setDeliveryDate(deliveryDate);

        return lease;
    }
}
