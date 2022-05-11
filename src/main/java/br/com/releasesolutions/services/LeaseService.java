package br.com.releasesolutions.services;

import br.com.releasesolutions.dao.LeaseDAO;
import br.com.releasesolutions.exceptions.MovieWithoutStockException;
import br.com.releasesolutions.exceptions.RentalException;
import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import br.com.releasesolutions.utils.DateUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.com.releasesolutions.utils.DateUtils.addDays;

public class LeaseService {

    private final LeaseDAO leaseDAO;
    private final SPCService spcService;

    private final EmailService emailService;

    public LeaseService(LeaseDAO dao, SPCService spc, EmailService email) {
        this.leaseDAO = dao;
        this.spcService = spc;
        this.emailService = email;
    }

    public Lease leaseMovie(User user, List<Movie> movies) throws MovieWithoutStockException, RentalException {

        if (user == null)
            throw new RentalException("User null.");

        if (movies == null || movies.isEmpty())
            throw new RentalException("Movie null.");

        for (Movie movie : movies) {
            if (movie.getStock() == 0)
                throw new MovieWithoutStockException();
        }

        boolean isNegatived;

        try {
            isNegatived = spcService.hasNegative(user);
        } catch (Exception e) {
            throw new RentalException("SPC service is unavailable. Try again!");
        }

        if (isNegatived) {
            throw new RentalException("User negatived.");
        }


        Lease lease = new Lease();
        lease.setMovies(movies);
        lease.setUser(user);
        lease.setLeaseDate(new Date());

        Double totalPrice = 0d;

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            Double moviePrice = movie.getLeasePrice();

            switch (i) {
                case 2:
                    moviePrice = moviePrice * 0.75;
                    break;
                case 3:
                    moviePrice = moviePrice * 0.50;
                    break;
                case 4:
                    moviePrice = moviePrice * 0.25;
                    break;
                case 5:
                    moviePrice = moviePrice * 0.0;
                    break;
                default:
                    break;
            }

            totalPrice += moviePrice;
        }

        lease.setPrice(totalPrice);

        Date deliveryDate = new Date();
        deliveryDate = addDays(deliveryDate, 1);

        boolean isSunday = DateUtils.checkDayOfWeek(deliveryDate, Calendar.SUNDAY);

        if (isSunday)
            deliveryDate = addDays(deliveryDate, 1);

        lease.setDeliveryDate(deliveryDate);

        leaseDAO.save(lease);

        return lease;
    }

    public void notifyDelays() {

        List<Lease> leases = leaseDAO.getPendingLeases();

        for (Lease lease : leases) {
            if (lease.getDeliveryDate().before(new Date())) {
                emailService.notifyDelay(lease.getUser());
            }
        }
    }
}
