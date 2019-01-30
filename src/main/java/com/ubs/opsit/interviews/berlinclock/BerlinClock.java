package com.ubs.opsit.interviews.berlinclock;

/**
 * Represents Berlin clock's state.
 * <p>
 * After instantiating represents "00:00:00" time.
 * To change it use {@link #setTime(int, int, int)} method.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Mengenlehreuhr">https://en.wikipedia.org/wiki/Mengenlehreuhr</a>
 *
 * @author Mikhail Chen-Len-Son
 */
class BerlinClock {

    static final String DELIM = System.lineSeparator();

    private final Light[] secondsLights;
    private final Light[] fiveHoursLights;
    private final Light[] hoursLights;
    private final Light[] fiveMinutesLights;
    private final Light[] minutesLights;

    /**
     * A fields matrix. Just to simplify traversing the rows and fields when printing.
     */
    private final Light[][] lights;

    BerlinClock() {
        this.secondsLights = new LightPanelBuilder()
                .yellow()
                .build();
        this.fiveHoursLights = new LightPanelBuilder()
                .red().red().red().red()
                .build();
        this.hoursLights = new LightPanelBuilder()
                .red().red().red().red()
                .build();
        this.fiveMinutesLights = new LightPanelBuilder()
                .yellow().yellow().red()
                .yellow().yellow().red()
                .yellow().yellow().red()
                .yellow().yellow()
                .build();
        this.minutesLights = new LightPanelBuilder()
                .yellow().yellow().yellow().yellow()
                .build();

        this.lights = new Light[][] {secondsLights, fiveHoursLights, hoursLights, fiveMinutesLights, minutesLights};
    }

    /**
     * Set time to the clock.
     *
     * @throws IllegalArgumentException if some of the values is negative or exceeds max value
     *
     * @param hours   hours value
     * @param minutes minutes values
     * @param seconds seconds values
     */
    void setTime(int hours, int minutes, int seconds) {
        validateValue(hours, 24);
        validateValue(minutes, 59);
        validateValue(seconds, 59);

        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    /**
     * Returns current time of the clock as a string using the following pattern:
     * <p>
     * Each string row represents a field's row of the clock from top to bottom.
     * Each character represents a single field.
     * <p>
     * The following characters are defined:
     * <ul>
     *     <li>R - Red light</li>
     *     <li>Y - Yellow light</li>
     *     <li>O - unlit</li>
     * </ul>
     *
     * @return formatted clock time
     */
    String printTime() {
        StringBuilder sb = new StringBuilder();
        String loopDelimiter = "";
        for (Light[] panel : lights) {
            sb.append(loopDelimiter);
            for (Light light : panel) {
                sb.append(light);
            }
            loopDelimiter = DELIM;
        }
        return sb.toString();
    }

    /**
     * Set the state for the top row of four red fields, which denote 5 full hours each,
     * and the second row of four red fields, which denote one full hour each.
     */
    private void setHours(int hoursGiven) {
        int fiveHours = hoursGiven / 5;
        int hours = hoursGiven % 5;

        // set the state for the fields, which denote 5 full hours each
        int i = 0;
        while (i < fiveHours) {
            fiveHoursLights[i++].setState(true);
        }
        while (i < fiveHoursLights.length) {
            fiveHoursLights[i++].setState(false);
        }

        // set the state for the fields, which denote one full hour each
        i = 0;
        while (i < hours) {
            hoursLights[i++].setState(true);
        }
        while (i < hoursLights.length) {
            hoursLights[i++].setState(false);
        }
    }

    /**
     * Set the state for the eleven yellow-and-red fields, which denote 5 full minutes each,
     * and four yellow fields, which denote one full minute each.
     */
    private void setMinutes(int minutesGiven) {
        int fiveMinutes = minutesGiven / 5;
        int minutes = minutesGiven % 5;

        // set the state for the fields, which denote 5 full minutes each
        int i = 0;
        while (i < fiveMinutes) {
            fiveMinutesLights[i++].setState(true);
        }
        while (i < fiveMinutesLights.length) {
            fiveMinutesLights[i++].setState(false);
        }

        // set the state for the fields, which denote one full minute each
        i = 0;
        while (i < minutes) {
            minutesLights[i++].setState(true);
        }
        while (i < minutesLights.length) {
            minutesLights[i++].setState(false);
        }
    }

    /**
     * Set the state for the blinking yellow light on top denoting even- or odd-numbered seconds.
     */
    private void setSeconds(int seconds) {
        secondsLights[0].setState(seconds % 2 == 0);
    }

    private static void validateValue(int value, int max) {
        if (value < 0 || value > max) {
            throw new IllegalArgumentException();
        }
    }
}
