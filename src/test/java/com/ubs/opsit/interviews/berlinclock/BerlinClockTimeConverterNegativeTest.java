package com.ubs.opsit.interviews.berlinclock;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Negative tests for {@link BerlinClockTimeConverter}.
 *
 * @author Mikhail Chen-Len-Son
 */
public class BerlinClockTimeConverterNegativeTest {

    private BerlinClockTimeConverter converter = new BerlinClockTimeConverter();

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenNullIsGiven() {
        converter.convertTime(null);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEmptyStringIsGiven() {
        String input = "";

        converter.convertTime(input);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInvalidStringIsGiven() {
        String input = "blabla";

        converter.convertTime(input);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNotEnoughNumbersGiven() {
        String input = "23:34";

        converter.convertTime(input);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenOneEmptyNumberIsGiven() {
        String input = "23:34:";

        converter.convertTime(input);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInvalidNumberIsGivenInHours() {
        String input = "25:34:45";

        converter.convertTime(input);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInvalidNumberIsGivenInMinutes() {
        String input = "22:65:45";

        converter.convertTime(input);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInvalidNumberIsGivenInSeconds() {
        String input = "22:12:60";

        converter.convertTime(input);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNegativeNumberIsGiven() {
        String input = "22:-12:59";

        converter.convertTime(input);

        fail();
    }
}
