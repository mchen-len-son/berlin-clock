package com.ubs.opsit.interviews.berlinclock;

/**
 * Light colour.
 *
 * @author Mikhail Chen-Len-Son
 */
public enum Colour {
    RED("R"),
    YELLOW("Y");

    private final String displayValue;

    Colour(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
