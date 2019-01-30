package com.ubs.opsit.interviews.berlinclock;

/**
 * Represents a single field of Berlin clock.
 * <p>
 * Can be of two colours (Red/Yellow) and two states (on/off).
 *
 * @author Mikhail Chen-Len-Son
 */
public class Light {

    /**
     * Value to print when unlit.
     */
    private static final String VALUE_OFF = "O";

    private final Colour colour;
    private boolean state;

    Light(Colour colour) {
        this.colour = colour;
    }

    void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state ? colour.toString() : VALUE_OFF;
    }
}
