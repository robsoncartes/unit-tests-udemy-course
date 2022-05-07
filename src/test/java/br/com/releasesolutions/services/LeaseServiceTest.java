package br.com.releasesolutions.services;

import br.com.releasesolutions.exceptions.MovieWithoutStockException;
import br.com.releasesolutions.exceptions.RentalException;
import br.com.releasesolutions.matchers.DayOfWeekMatcher;
import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import br.com.releasesolutions.utils.DateUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.com.releasesolutions.builders.MovieBuilder.getMovieBuilderInstance;
import static br.com.releasesolutions.builders.UserBuilder.getUserBuilderInstance;
import static br.com.releasesolutions.matchers.CustomMatcher.is;
import static br.com.releasesolutions.matchers.CustomMatcher.isMonday;
import static br.com.releasesolutions.matchers.CustomMatcher.today;
import static br.com.releasesolutions.matchers.CustomMatcher.todayWithDaysOfDifference;
import static br.com.releasesolutions.matchers.CustomMatcher.tomorrow;
import static br.com.releasesolutions.utils.DateUtils.getDateWithDaysDifference;
import static br.com.releasesolutions.utils.DateUtils.isSameDate;
import static java.util.Calendar.MONDAY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

public class LeaseServiceTest {

    private LeaseService leaseService;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {

        // Common scenery
        leaseService = new LeaseService();
    }

    @Test
    public void test_shouldRentMovie() {

        // Scenery
        assumeFalse(DateUtils.checkDayOfWeek(new Date(), Calendar.SATURDAY));
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getWithLeasePriceEqualsTo(5.0).getMovie());

        // action

        try {
            // FIXME
            Lease lease = leaseService.leaseMovie(user, movies);

            // verifications
            assertThat(lease.getPrice(), is(equalTo(5.0)));
            assertThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
            assertThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));

        } catch (Exception e) {
            // FIXME
            e.printStackTrace();
            fail("Should not throw exception.");
        }
    }

    @Test(expected = MovieWithoutStockException.class)
    public void test_shouldNotRentMovieWithoutStockUsingRule() throws Exception {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getWithoutStock().getMovie());

        // action
        leaseService.leaseMovie(user, movies);
    }

    @Test
    public void test_shouldRentMovieUsingRule2() throws Exception {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        assumeFalse(DateUtils.checkDayOfWeek(new Date(), Calendar.SATURDAY));

        List<Movie> movies = List.of(
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie()
        );

        // Action
        // FIXME
        Lease lease = leaseService.leaseMovie(user, movies);

        // Verifications
        // 4 + 4 = 8
        error.checkThat(lease.getPrice(), is(equalTo(8.0)));
        error.checkThat(isSameDate(lease.getLeaseDate(), new Date()), is(true));
        error.checkThat(isSameDate(lease.getDeliveryDate(), getDateWithDaysDifference(1)), is(true));
    }

    @Test
    public void test_shouldRentMovieAndUsingRuleAndCustomMatchers() throws Exception {

        // Scenery
        assumeFalse(DateUtils.checkDayOfWeek(new Date(), Calendar.SATURDAY));
        User user = getUserBuilderInstance().getUser();

        List<Movie> movies = List.of(
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie()
        );

        // Action
        // FIXME
        Lease lease = leaseService.leaseMovie(user, movies);

        // Verifications
        // 4 + 4 = 8
        error.checkThat(lease.getPrice(), is(equalTo(8.0)));
        error.checkThat(lease.getLeaseDate(), today());
        error.checkThat(lease.getDeliveryDate(), todayWithDaysOfDifference(1));
    }

    @Test
    public void test_shouldRentMovieAndUsingRuleAndCustomMatchers2() throws Exception {

        // Scenery
        assumeFalse(DateUtils.checkDayOfWeek(new Date(), Calendar.SATURDAY));
        User user = getUserBuilderInstance().getUser();

        List<Movie> movies = List.of(
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie()
        );

        // Action
        // FIXME
        Lease lease = leaseService.leaseMovie(user, movies);

        // Verifications
        // 4 + 4 = 8
        error.checkThat(lease.getPrice(), is(equalTo(8.0)));
        error.checkThat(lease.getLeaseDate(), today());
        error.checkThat(lease.getDeliveryDate(), tomorrow());
    }

    @Test
    public void test_shouldRentMovieAndUsingCustomMatchers() {

        // Scenery
        assumeFalse(DateUtils.checkDayOfWeek(new Date(), Calendar.SATURDAY));
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        // action

        try {
            Lease lease = leaseService.leaseMovie(user, movies);

            // verifications
            assertThat(lease.getPrice(), is(equalTo(4.0)));
            assertThat(lease.getLeaseDate(), today());
            assertThat(lease.getDeliveryDate(), todayWithDaysOfDifference(1));

        } catch (Exception e) {
            // FIXME
            e.printStackTrace();
            fail("Should not throw exception.");
        }
    }

    @Test
    public void test_shouldNotRentMovieWithoutStock() throws Exception {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getWithoutStock().getMovie());

        expectedException.expect(Exception.class);

        // action
        leaseService.leaseMovie(user, movies);
    }

    @Test
    public void test_shouldNotRentMovieWithoutUser() throws MovieWithoutStockException {

        // best form of implementation

        // Scenery
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        // action

        try {
            leaseService.leaseMovie(null, movies);
            fail();

        } catch (RentalException e) {
            assertThat(e.getMessage(), is("User null."));
        }
    }

    @Test
    public void test_shouldNotRentMovieWithoutMovie() throws RentalException, MovieWithoutStockException {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        expectedException.expect(RentalException.class);
        expectedException.expectMessage("Movie null.");

        // Actions
        leaseService.leaseMovie(user, null);
    }

    @Test
    public void test_shouldPay75PercentOnMovieThree() throws RentalException, MovieWithoutStockException {

        // Scenery
        User user = getUserBuilderInstance().getUser();

        List<Movie> movies = List.of(
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie()
        );

        // Action
        // FIXME
        Lease lease = leaseService.leaseMovie(user, movies);

        // verification
        // 4 + 4 + 3 = 11
        assertThat(lease.getPrice(), is(11.0));

    }

    @Test
    public void test_shouldPay50PercentOnMovieFour() throws RentalException, MovieWithoutStockException {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(
                new Movie("Movie 1", 1, 4.0),
                new Movie("Movie 2", 2, 4.0),
                new Movie("Movie 3", 3, 4.0),
                new Movie("Movie 4", 4, 4.0));

        // Action
        // FIXME
        Lease lease = leaseService.leaseMovie(user, movies);

        // verification
        // 4 + 4 + 3 + 2 = 13
        assertThat(lease.getPrice(), is(13.0));

    }

    @Test
    public void test_shouldPay25PercentOnMovieFive() throws RentalException, MovieWithoutStockException {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie());

        // Action
        // FIXME
        Lease lease = leaseService.leaseMovie(user, movies);

        // verification
        // 4 + 4 + 3 + 2 + 1 = 14
        assertThat(lease.getPrice(), is(14.0));
    }

    @Test
    public void test_shouldLeaseForFreeOnMovieSix() throws RentalException, MovieWithoutStockException {

        // Scenery
        User user = getUserBuilderInstance().getUser();

        List<Movie> movies = List.of(
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie(),
                getMovieBuilderInstance().getMovie()
        );

        // Action
        // FIXME
        Lease lease = leaseService.leaseMovie(user, movies);

        // verification
        // 4 + 4 + 3 + 2 + 1 + 0 = 14
        assertThat(lease.getPrice(), is(14.0));
    }

    @Test
    public void test_shouldDeliveryMovieOnMondayIfMovieIsLeaseOnSaturday_1() throws RentalException, MovieWithoutStockException {

        assumeTrue(DateUtils.checkDayOfWeek(new Date(), Calendar.SATURDAY));

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        // Action
        Lease delivery = leaseService.leaseMovie(user, movies);

        // Verification
        assertThat(delivery.getDeliveryDate(), new DayOfWeekMatcher(Calendar.MONDAY));
    }

    @Test
    public void test_shouldDeliveryMovieOnMondayIfMovieIsLeaseOnSaturday_2() throws RentalException, MovieWithoutStockException {

        assumeTrue(DateUtils.checkDayOfWeek(new Date(), Calendar.SATURDAY));

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        // Action
        Lease delivery = leaseService.leaseMovie(user, movies);

        // Verification
        assertThat(delivery.getDeliveryDate(), is(MONDAY));
    }

    @Test
    public void test_shouldDeliveryMovieOnMondayIfMovieIsLeaseOnSaturday_3() throws RentalException, MovieWithoutStockException {

        assumeTrue(DateUtils.checkDayOfWeek(new Date(), Calendar.SATURDAY));

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        // Action
        Lease delivery = leaseService.leaseMovie(user, movies);

        // Verification
        assertThat(delivery.getDeliveryDate(), isMonday());
    }
}
