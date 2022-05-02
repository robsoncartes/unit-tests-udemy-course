package br.com.releasesolutions.services;

import br.com.releasesolutions.exceptions.MovieWithoutStockException;
import br.com.releasesolutions.exceptions.RentalException;
import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;

import java.util.Date;
import java.util.List;

import static br.com.releasesolutions.utils.DateUtils.addDays;

public class LeaseService {

    public Lease leaseMovie(User user, List<Movie> movies) throws MovieWithoutStockException, RentalException {

        if (user == null)
            throw new RentalException("User null.");

        if (movies == null || movies.isEmpty())
            throw new RentalException("Movie null.");

        for(Movie movie: movies) {
            if (movie.getStock() == 0)
                throw new MovieWithoutStockException();
        }


        Lease lease = new Lease();
        lease.setMovies(movies);
        lease.setUser(user);
        lease.setLeaseDate(new Date());

        Double totalPrice = 0d;
        for (Movie movie: movies){
            totalPrice += movie.getLeasePrice();
        }

        lease.setPrice(totalPrice);


        Date deliveryDate = new Date();
        deliveryDate = addDays(deliveryDate, 1);
        lease.setDeliveryDate(deliveryDate);

        return lease;
    }
}
