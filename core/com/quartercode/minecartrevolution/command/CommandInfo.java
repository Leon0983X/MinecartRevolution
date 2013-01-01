
package com.quartercode.minecartrevolution.command;

public class CommandInfo {

    private final boolean  ignoreCase;
    private final String   parameterUsage;
    private final String   description;
    private final String   permission;
    private final String[] labels;

    public CommandInfo(final boolean ignoreCase, final String parameterUsage, final String description, final String permission, final String... labels) {

        this.ignoreCase = ignoreCase;
        this.parameterUsage = parameterUsage;
        this.description = description;
        this.permission = "command." + permission;
        this.labels = labels;
    }

    public boolean isIgnoreCase() {

        return ignoreCase;
    }

    public String getParameterUsage() {

        return parameterUsage;
    }

    public String getDescription() {

        return description;
    }

    public String getPermission() {

        return permission;
    }

    public String[] getLabels() {

        return labels;
    }

}
