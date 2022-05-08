package br.com.releasesolutions.builders;

import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import java.util.Date;
import java.util.List;

import static br.com.releasesolutions.builders.MovieBuilder.getMovieBuilderInstance;
import static br.com.releasesolutions.builders.UserBuilder.getUserBuilderInstance;
import static br.com.releasesolutions.utils.DateUtils.getDateWithDaysDifference;

public class LeaseBuilder {

    private Lease lease;

    private LeaseBuilder() {

    }

    public static LeaseBuilder getLeaseBuilderInstance() {

        LeaseBuilder builder = new LeaseBuilder();
        initializeDefaultData(builder);

        return builder;
    }

    public static void initializeDefaultData(LeaseBuilder builder) {

        builder.lease = new Lease();
        Lease lease = builder.lease;

        lease.setUser(getUserBuilderInstance().getUser());
        lease.setMovies(List.of(getMovieBuilderInstance().getMovie()));
        lease.setLeaseDate(new Date());
        lease.setDeliveryDate(getDateWithDaysDifference(1));
        lease.setPrice(4.0);
    }

    public LeaseBuilder getLeaseWithUser(User user) {

        lease.setUser(user);

        return this;
    }

    public LeaseBuilder getLeaseWithMovieList(Movie... movies) {

        lease.setMovies(List.of(movies));

        return this;
    }

    public LeaseBuilder getLeaseWithLeaseDate(Date date) {

        lease.setLeaseDate(date);

        return this;
    }

    public LeaseBuilder getLeaseWithDeliveryDate(Date date) {

        lease.setDeliveryDate(date);

        return this;
    }

    public LeaseBuilder delay() {

        lease.setLeaseDate(getDateWithDaysDifference(-4));
        lease.setDeliveryDate(getDateWithDaysDifference(-2));

        return this;
    }

    public LeaseBuilder withPrice(Double price) {

        lease.setPrice(price);

        return this;
    }

    public Lease getLease() {

        return lease;
    }
}
