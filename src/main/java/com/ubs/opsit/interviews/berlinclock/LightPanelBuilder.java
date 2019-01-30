package com.ubs.opsit.interviews.berlinclock;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for light's panel for Berlin clock.
 *
 * @author Mikhail Chen-Len-Son
 */
class LightPanelBuilder {
    private List<Light> lights = new ArrayList<>(4);

    LightPanelBuilder yellow() {
        lights.add(new Light(Colour.YELLOW));
        return this;
    }

    LightPanelBuilder red() {
        lights.add(new Light(Colour.RED));
        return this;
    }

    Light[] build() {
        return lights.toArray(new Light[0]);
    }
}
