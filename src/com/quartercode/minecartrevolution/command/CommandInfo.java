
package com.quartercode.minecartrevolution.command;

public class CommandInfo {

    private boolean  ignoreCase;
    private String   parameterUsage;
    private String   description;
    private String   permission;
    private String[] labels;

    public CommandInfo(boolean ignoreCase, String parameterUsage, String description, String permission, String... labels) {

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
