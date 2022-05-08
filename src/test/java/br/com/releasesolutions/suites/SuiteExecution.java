package br.com.releasesolutions.suites;

import br.com.releasesolutions.services.AssertTest;
import br.com.releasesolutions.services.CalculatorMockTest;
import br.com.releasesolutions.services.CalculatorTest;
import br.com.releasesolutions.services.LeaseServiceTest;
import br.com.releasesolutions.services.LeaseValueCalculationTest;
import br.com.releasesolutions.services.TestOrdination;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AssertTest.class,
        CalculatorTest.class,
        CalculatorMockTest.class,
        LeaseServiceTest.class,
        LeaseValueCalculationTest.class,
        TestOrdination.class
})
public class SuiteExecution {

    @BeforeClass
    public static void before() {
        System.out.println("Before");
    }

    @AfterClass
    public static void after() {
        System.out.println("After");
    }
}
