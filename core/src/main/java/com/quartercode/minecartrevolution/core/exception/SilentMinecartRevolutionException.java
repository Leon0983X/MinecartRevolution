/*
 * This file is part of MinecartRevolution-Core.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Core. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.core.exception;

import com.quartercode.minecartrevolution.core.MinecartRevolution;

public class SilentMinecartRevolutionException extends MinecartRevolutionException {

    public SilentMinecartRevolutionException(MinecartRevolution minecartRevolution, String message) {

        super(minecartRevolution, message);
    }

    public SilentMinecartRevolutionException(MinecartRevolution minecartRevolution, Throwable cause, String message) {

        super(minecartRevolution, cause, message);
    }

}
