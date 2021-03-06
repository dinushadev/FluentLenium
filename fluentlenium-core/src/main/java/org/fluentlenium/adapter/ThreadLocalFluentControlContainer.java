package org.fluentlenium.adapter;

import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.FluentDriver;

/**
 * Alternative {@link FluentControlContainer} implementation, using a ThreadLocal variable to store the
 * {@link FluentDriver} instance.
 */
public class ThreadLocalFluentControlContainer implements FluentControlContainer {
    private ThreadLocal<FluentControl> fluentControls = new ThreadLocal<>();

    @Override
    public FluentControl getFluentControl() {
        return fluentControls.get();
    }

    @Override
    public void setFluentControl(FluentControl fluentControl) {
        if (fluentControl == null) {
            this.fluentControls.remove();
        } else {
            this.fluentControls.set(fluentControl);
        }
    }
}
