
package com.quartercode.minecartrevolution.exception;

import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.quarterbukkit.api.exception.GameException;

public class MinecartRevolutionException extends GameException {

    private final MinecartRevolution minecartRevolution;
    private Throwable                cause;

    public MinecartRevolutionException(final MinecartRevolution minecartRevolution) {

        super(minecartRevolution.getPlugin());
        this.minecartRevolution = minecartRevolution;
    }

    public MinecartRevolutionException(final MinecartRevolution minecartRevolution, final Throwable cause) {

        super(minecartRevolution.getPlugin());
        this.minecartRevolution = minecartRevolution;
        this.cause = cause;
    }

    public MinecartRevolutionException(final MinecartRevolution minecartRevolution, final String message) {

        super(minecartRevolution.getPlugin(), message);
        this.minecartRevolution = minecartRevolution;
    }

    public MinecartRevolutionException(final MinecartRevolution minecartRevolution, final Throwable cause, final String message) {

        super(minecartRevolution.getPlugin(), message);
        this.minecartRevolution = minecartRevolution;
        this.cause = cause;
    }

    public MinecartRevolution getMinecartRevolution() {

        return minecartRevolution;
    }

    public Throwable getCause() {

        return cause;
    }

}
