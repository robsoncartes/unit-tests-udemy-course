package br.com.releasesolutions.services;

import br.com.releasesolutions.exceptions.MovieWithoutStockException;
import br.com.releasesolutions.exceptions.RentalException;
import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static br.com.releasesolutions.builders.UserBuilder.getUserBuilderInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class LeaseValueCalculationTest {

    private LeaseService leaseService;

    @Parameterized.Parameter
    public List<Movie> movies;

    @Parameterized.Parameter(value = 1)
    public Double leasePrice;

    @Parameterized.Parameter(value = 2)
    public String scenery;

    private static final Movie movie1 = new Movie("Movie 1", 2, 4.0);
    private static final Movie movie2 = new Movie("Movie 2", 2, 4.0);
    private static final Movie movie3 = new Movie("Movie 3", 2, 4.0);
    private static final Movie movie4 = new Movie("Movie 4", 2, 4.0);
    private static final Movie movie5 = new Movie("Movie 5", 2, 4.0);
    private static final Movie movie6 = new Movie("Movie 6", 2, 4.0);
    private static final Movie movie7 = new Movie("Movie 7", 2, 4.0);

    @Before
    public void setup() {

        // Common Scenery
        leaseService = new LeaseService();

    }

    @Parameterized.Parameters(name = "Test {index} {2}")
    public static Collection<Object> getParameters() {

        // Scenery
        return Arrays.asList(new Object[][]{
                {List.of(movie1, movie2), 8.0, " - 2nd movie: no discount."},
                {List.of(movie1, movie2, movie3), 11.0, " - 3rd movie: 25%."},
                {List.of(movie1, movie2, movie3, movie4), 13.0, " - 4th movie: 50%."},
                {List.of(movie1, movie2, movie3, movie4, movie5), 14.0, " - 5th movie: 75%."},
                {List.of(movie1, movie2, movie3, movie4, movie5, movie6), 14.0, " - 6th movie: 100%."},
                {List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7), 18.0, " - 7th movie: no discount."}
        });
    }

    @Test
    public void test_shouldCalculateLeaseValueConsideringDiscounts() throws RentalException, MovieWithoutStockException {

        // Scenery
        User user = getUserBuilderInstance().getUser();

        // Action
        Lease lease = leaseService.leaseMovie(user, movies);

        // verification
        // 4 + 4 + 3 + 2 + 1 = 14
        assertThat(lease.getPrice(), is(leasePrice));
    }
}
