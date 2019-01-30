package com.ubs.opsit.interviews.berlinclock;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Berlin Clock time converter test suite.
 *
 * @author Mikhail Chen-Len-Son
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BerlinClockTimeConverterParametrizedTest.class,
        BerlinClockTimeConverterNegativeTest.class,
        BerlinClockTimeConverterConcurrencyTest.class})
public class BerlinClockTimeConverterTestSuite {
}
