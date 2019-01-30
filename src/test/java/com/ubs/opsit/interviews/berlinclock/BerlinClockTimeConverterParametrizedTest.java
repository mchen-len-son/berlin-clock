package com.ubs.opsit.interviews.berlinclock;

import static com.ubs.opsit.interviews.berlinclock.BerlinClock.DELIM;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Happy path and border tests for {@link BerlinClockTimeConverter}.
 *
 * @author Mikhail Chen-Len-Son
 */
@RunWith(Parameterized.class)
public class BerlinClockTimeConverterParametrizedTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"00:00:00", "Y" + DELIM + "OOOO" + DELIM + "OOOO" + DELIM + "OOOOOOOOOOO" + DELIM + "OOOO"},
                {"00:00:01", "O" + DELIM + "OOOO" + DELIM + "OOOO" + DELIM + "OOOOOOOOOOO" + DELIM + "OOOO"},
                {"20:00:00", "Y" + DELIM + "RRRR" + DELIM + "OOOO" + DELIM + "OOOOOOOOOOO" + DELIM + "OOOO"},
                {"10:00:00", "Y" + DELIM + "RROO" + DELIM + "OOOO" + DELIM + "OOOOOOOOOOO" + DELIM + "OOOO"},
                {"04:00:00", "Y" + DELIM + "OOOO" + DELIM + "RRRR" + DELIM + "OOOOOOOOOOO" + DELIM + "OOOO"},
                {"02:00:00", "Y" + DELIM + "OOOO" + DELIM + "RROO" + DELIM + "OOOOOOOOOOO" + DELIM + "OOOO"},
                {"24:00:00", "Y" + DELIM + "RRRR" + DELIM + "RRRR" + DELIM + "OOOOOOOOOOO" + DELIM + "OOOO"},
                {"00:55:00", "Y" + DELIM + "OOOO" + DELIM + "OOOO" + DELIM + "YYRYYRYYRYY" + DELIM + "OOOO"},
                {"00:30:00", "Y" + DELIM + "OOOO" + DELIM + "OOOO" + DELIM + "YYRYYROOOOO" + DELIM + "OOOO"},
                {"00:04:00", "Y" + DELIM + "OOOO" + DELIM + "OOOO" + DELIM + "OOOOOOOOOOO" + DELIM + "YYYY"},
                {"00:02:00", "Y" + DELIM + "OOOO" + DELIM + "OOOO" + DELIM + "OOOOOOOOOOO" + DELIM + "YYOO"},
                {"00:59:00", "Y" + DELIM + "OOOO" + DELIM + "OOOO" + DELIM + "YYRYYRYYRYY" + DELIM + "YYYY"},
                {"23:59:59", "O" + DELIM + "RRRR" + DELIM + "RRRO" + DELIM + "YYRYYRYYRYY" + DELIM + "YYYY"},
        });
    }

    private BerlinClockTimeConverter converter = new BerlinClockTimeConverter();

    private String input;
    private String output;

    public BerlinClockTimeConverterParametrizedTest(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Test
    public void shouldReturnExpectedValue() {
        assertEquals(output, converter.convertTime(input));
    }
}
