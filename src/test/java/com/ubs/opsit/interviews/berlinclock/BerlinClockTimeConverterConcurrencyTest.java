package com.ubs.opsit.interviews.berlinclock;

import static com.ubs.opsit.interviews.berlinclock.BerlinClock.DELIM;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Concurrency tests for {@link BerlinClockTimeConverter}.
 *
 * @author Mikhail Chen-Len-Son
 */
public class BerlinClockTimeConverterConcurrencyTest {

    private static final int TASKS_COUNT = 1000;
    private static final int THREADS_COUNT = 100;

    private static final String[][] TEST_DATA = new String[][]{
        {"00:00:00", "Y" + DELIM + "OOOO" + DELIM + "OOOO" + DELIM + "OOOOOOOOOOO" + DELIM + "OOOO"},
        {"23:59:59", "O" + DELIM + "RRRR" + DELIM + "RRRO" + DELIM + "YYRYYRYYRYY" + DELIM + "YYYY"}
    };

    private BerlinClockTimeConverter converter = new BerlinClockTimeConverter();

    /**
     * Given multiple requests for conversion of different time values.
     * <p>
     * When those requests are running in parallel,
     * the converter should keep providing expected formatted value.
     */
    @Test
    public void shouldReturnExpectedValuesWhenRunningInParallel() throws ExecutionException, InterruptedException {
        List<Execution> executions = new ArrayList<>(TASKS_COUNT);
        ExecutorService executors = Executors.newFixedThreadPool(THREADS_COUNT);

        for (int i = 0; i < TASKS_COUNT; i++) {
            // add task to turn off all lights ('00:00:00' means all lights are off)
            Future<String> litAll = executors.submit(() -> converter.convertTime(TEST_DATA[0][0]));
            executions.add(new Execution(TEST_DATA[0][1], litAll));

            // add task to turn on all lights ('23:59:59' means all lights are on)
            Future<String> unlitAll = executors.submit(() -> converter.convertTime(TEST_DATA[1][0]));
            executions.add(new Execution(TEST_DATA[1][1], unlitAll));
        }

        for (Execution execution : executions) {
            assertEquals(execution.getExpected(), execution.getActual());
        }
    }

    private class Execution {
        private final String expected;
        private final Future<String> actual;

        Execution(String expected, Future<String> actual) {
            this.expected = expected;
            this.actual = actual;
        }

        String getExpected() {
            return expected;
        }

        String getActual() throws ExecutionException, InterruptedException {
            return actual.get();
        }
    }
}
