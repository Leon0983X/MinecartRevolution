
package com.quartercode.minecartrevolution.exception;

import com.quartercode.minecartrevolution.MinecartRevolution;

public class MinecartRevolutionSilenceException extends MinecartRevolutionException {

    private static final long serialVersionUID = -1325581518215061260L;

    public MinecartRevolutionSilenceException(final MinecartRevolution minecartRevolution) {

        super(minecartRevolution);
    }

    public MinecartRevolutionSilenceException(final MinecartRevolution minecartRevolution, final Throwable cause) {

        super(minecartRevolution, cause);
    }

    public MinecartRevolutionSilenceException(final MinecartRevolution minecartRevolution, final String message) {

        super(minecartRevolution, message);
    }

    public MinecartRevolutionSilenceException(final MinecartRevolution minecartRevolution, final Throwable cause, final String message) {

        super(minecartRevolution, cause, message);
    }

}
