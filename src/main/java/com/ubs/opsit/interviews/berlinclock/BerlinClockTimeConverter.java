package com.ubs.opsit.interviews.berlinclock;

import com.ubs.opsit.interviews.TimeConverter;

import java.util.Objects;

/**
 * Berlin clock time converter.
 * <p>
 * Parses given string formatted as "HH:mm:ss"
 * and returns string representing Berlin Clock time.
 * <p>
 * Each string row represents a field's row of the clock from top to bottom.
 * Each character represents a single field.
 * <p>
 * The following characters are defined:
 * <ul>
 *     <li>R - Red light</li>
 *     <li>Y - Yellow light</li>
 *     <li>O - unlit field</li>
 * </ul>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Mengenlehreuhr">https://en.wikipedia.org/wiki/Mengenlehreuhr</a>
 *
 * @author Mikhail Chen-Len-Son
 */
public class BerlinClockTimeConverter implements TimeConverter {

    private static final String TIME_DELIMITER = ":";

    private final BerlinClock berlinClock = new BerlinClock();

    /**
     * Converts the given ISO time to Berlin Clock time representation.
     * <p>
     *
     * @throws  IllegalArgumentException
     *          If any invalid character is found in {@code aTime}
     *          or it does not correspond to "HH:mm:ss" format
     *
     * @param aTime time string formatted as "HH:mm:ss"
     * @return text representation of Berlin Clock time
     */
    @Override
    public String convertTime(String aTime) {
        Objects.requireNonNull(aTime);

        String[] parts = aTime.split(TIME_DELIMITER);
        if (parts.length != 3) {
            throw new IllegalArgumentException();
        }

        try {
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);

            /*
             * To avoid having inconsistent state in multithreading environment
             * we need to make sure we have an exclusive access to the clock.
             *
             * A simple intrinsic lock should be enough here.
             * A possible further optimization is to divide clock state gathering and rendering,
             * as to be able to release the lock earlier, once we have got the state.
             * This would make us break encapsulation to expose clock's state,
             * so I considered it as premature optimization and out-of-scope.
             */
            synchronized (berlinClock) {
                berlinClock.setTime(hours, minutes, seconds);
                return berlinClock.printTime();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
