package br.com.releasesolutions.services;

import br.com.releasesolutions.dao.LeaseDAO;
import br.com.releasesolutions.matchers.DayOfWeekMatcher;
import br.com.releasesolutions.models.Lease;
import br.com.releasesolutions.models.Movie;
import br.com.releasesolutions.models.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.com.releasesolutions.builders.MovieBuilder.getMovieBuilderInstance;
import static br.com.releasesolutions.builders.UserBuilder.getUserBuilderInstance;
import static br.com.releasesolutions.matchers.CustomMatcher.is;
import static br.com.releasesolutions.matchers.CustomMatcher.isMonday;
import static br.com.releasesolutions.utils.DateUtils.getDate;
import static br.com.releasesolutions.utils.DateUtils.isSameDate;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MAY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.whenNew;


@RunWith(PowerMockRunner.class)
@PrepareForTest({LeaseService.class})
public class LeaseServiceWithPowerMockTest {

    @InjectMocks
    private LeaseService leaseService;

    @Mock
    private SPCService spcService;

    @Mock
    private LeaseDAO leaseDAO;

    @Rule //initMocks
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {

        // Common scenery
        leaseService = PowerMockito.spy(leaseService);
    }

    @Test
    public void test_shouldRentMovie() throws Exception {

        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        whenNew(Date.class).withNoArguments().thenReturn(getDate(21, 5, 2022));

        // Action
        Lease lease = leaseService.leaseMovie(user, movies);

        // Verifications
        // 4 + 4 = 8
        error.checkThat(lease.getPrice(), is(equalTo(4.0)));
        error.checkThat(isSameDate(lease.getLeaseDate(), getDate(21, 5, 2022)), is(true));
        error.checkThat(isSameDate(lease.getDeliveryDate(), getDate(23, 5, 2022)), is(true));
    }

    @Test
    public void test_shouldRentMovieAndUsingRuleAndCustomMatchers() throws Exception {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        whenNew(Date.class).withNoArguments().thenReturn(getDate(14, 5, 2022));
        // Action
        Lease lease = leaseService.leaseMovie(user, movies);

        // Verifications
        assertThat(lease.getDeliveryDate(), isMonday());
    }


    @Test
    public void test_shouldDeliveryMovieOnMondayIfMovieIsLeaseOnSaturday_1() throws Exception {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        whenNew(Date.class).withNoArguments().thenReturn(getDate(14, 5, 2022));

        // Action
        Lease delivery = leaseService.leaseMovie(user, movies);

        // Verification
        assertThat(delivery.getDeliveryDate(), new DayOfWeekMatcher(MONDAY));
    }

    @Test
    public void test_shouldDeliveryMovieOnMondayIfMovieIsLeaseOnSaturday_2() throws Exception {

        // Scenery

        Calendar calendar = Calendar.getInstance();
        calendar.set(DAY_OF_MONTH, 16);
        calendar.set(MONTH, MAY);
        calendar.set(YEAR, 2022);
        PowerMockito.mockStatic(Calendar.class);
        PowerMockito.when(getInstance()).thenReturn(calendar);

        // Action

        assertThat(getInstance().getTime(), is(MONDAY));

        PowerMockito.verifyStatic(Calendar.class, times(1));
        Calendar.getInstance();
    }

    @Test
    public void test_shouldDeliveryMovieOnMondayIfMovieIsLeaseOnSaturday_3() throws Exception {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        whenNew(Date.class).withNoArguments().thenReturn(getDate(14, 5, 2022));

        // Action
        Lease delivery = leaseService.leaseMovie(user, movies);

        // Verification
        assertThat(delivery.getDeliveryDate(), isMonday());
    }

    @Test
    public void test_shouldRentMovieWithoutCalculatingPrice() throws Exception {

        // Scenery
        User user = getUserBuilderInstance().getUser();
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        PowerMockito.doReturn(1.0).when(leaseService, "getTotalPrice", movies);

        // Action
        Lease lease = leaseService.leaseMovie(user, movies);

        // Verification
        assertThat(lease.getPrice(), is(1.0));
        PowerMockito.verifyPrivate(leaseService).invoke("getTotalPrice", movies);
    }

    @Test
    public void test_shouldCalculatingPrice() throws Exception {

        // Scenery
        List<Movie> movies = List.of(getMovieBuilderInstance().getMovie());

        // Action
        Double price = Whitebox.invokeMethod(leaseService, "getTotalPrice", movies);

        // Verification
        assertThat(price, is(4.0));
    }
}
