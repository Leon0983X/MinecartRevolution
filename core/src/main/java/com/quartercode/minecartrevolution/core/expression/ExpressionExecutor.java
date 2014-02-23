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

package com.quartercode.minecartrevolution.core.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public class ExpressionExecutor {

    private static final String              COMMAND_END_MARKER    = ";";
    private static final char[]              OPERATORS             = { '+', '-', '*', '/' };

    private static final ScriptEngineManager SCRIPT_ENGINE_MANAGER = new ScriptEngineManager();

    public static String getExpression(String[] lines) {

        String expression = "";

        for (String line : lines) {
            if (line != null && !line.isEmpty()) {
                if (!line.endsWith(COMMAND_END_MARKER)) {
                    line += COMMAND_END_MARKER;
                }

                expression += line;
            }
        }

        return expression;
    }

    private final MinecartRevolution          minecartRevolution;

    private List<ExpressionCommand>           expressionCommands;
    private List<ExpressionConstant>          expressionConstants;
    private final Map<Minecart, List<String>> minecartExpressions;

    public ExpressionExecutor(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;

        setExpressionCommands(new ArrayList<ExpressionCommand>());
        setExpressionConstants(new ArrayList<ExpressionConstant>());
        minecartExpressions = new HashMap<Minecart, List<String>>();
    }

    public List<ExpressionCommand> getExpressionCommands() {

        return Collections.unmodifiableList(expressionCommands);
    }

    public void setExpressionCommands(List<ExpressionCommand> expressionCommands) {

        this.expressionCommands = expressionCommands;
    }

    public void addExpressionCommand(ExpressionCommand expressionCommand) {

        expressionCommand.setMinecartRevolution(minecartRevolution);
        expressionCommands.add(expressionCommand);
        expressionCommand.enable();
    }

    public List<ExpressionConstant> getExpressionConstants() {

        return Collections.unmodifiableList(expressionConstants);
    }

    public void setExpressionConstants(List<ExpressionConstant> expressionConstants) {

        this.expressionConstants = expressionConstants;
    }

    public void addExpressionConstant(ExpressionConstant expressionConstant) {

        expressionConstant.setMinecartRevolution(minecartRevolution);
        expressionConstants.add(expressionConstant);
        expressionConstant.enable();
    }

    public Map<Minecart, List<String>> getMinecartExpressions() {

        return Collections.unmodifiableMap(minecartExpressions);
    }

    public void execute(Minecart minecart, String expression) {

        expression = expression.trim();
        String[] expressionParts = expression.split(COMMAND_END_MARKER);

        for (String expressionPart : expressionParts) {
            if (expressionPart.equalsIgnoreCase("-") || expressionPart.startsWith("remove") || expressionPart.equalsIgnoreCase("- all") || expressionPart.startsWith("remove all")) {
                if (minecartExpressions.containsKey(minecart)) {
                    minecartExpressions.remove(minecart);
                }
            } else if (expressionPart.startsWith("+ ") || expressionPart.startsWith("add ")) {
                if (!minecartExpressions.containsKey(minecart)) {
                    minecartExpressions.put(minecart, new ArrayList<String>());
                }
                minecartExpressions.get(minecart).add(expressionPart.replaceAll("\\+ ", "").replaceAll("add ", ""));
            } else if (expressionPart.startsWith("- ") || expressionPart.startsWith("remove ")) {
                if (minecartExpressions.containsKey(minecart)) {
                    String removalExpressionPart = expressionPart.replaceAll("\\- ", "").replaceAll("remove ", "");
                    if (removalExpressionPart.split(" ").length == 1) {
                        for (String minecartExpression : new ArrayList<String>(minecartExpressions.get(minecart))) {
                            if (minecartExpression.startsWith(removalExpressionPart)) {
                                minecartExpressions.get(minecart).remove(minecartExpression);
                            }
                        }
                    } else {
                        for (String minecartExpression : new ArrayList<String>(minecartExpressions.get(minecart))) {
                            if (minecartExpression.equalsIgnoreCase(removalExpressionPart)) {
                                minecartExpressions.get(minecart).remove(minecartExpression);
                            }
                        }
                    }

                    if (minecartExpressions.get(minecart).isEmpty()) {
                        minecartExpressions.remove(minecart);
                    }
                }
            } else {
                String[] splittedExpressionPart = splitExpression(expressionPart);

                for (ExpressionCommand expressionCommand : expressionCommands) {
                    String command = splittedExpressionPart[0];

                    if (command != null) {
                        ExpressionCommandInfo info = expressionCommand.getInfo();

                        for (String ecCommandLabel : info.getCommandLabels()) {
                            if (ecCommandLabel.equalsIgnoreCase(command)) {
                                if (expressionCommand.canExecute(minecart)) {
                                    if (splittedExpressionPart.length == 1 && info.getTypeArray().hasType(null)) {
                                        expressionCommand.execute(minecart, null);
                                        continue;
                                    } else if (splittedExpressionPart.length == 2) {
                                        String parameterString = splittedExpressionPart[1];
                                        parameterString = replaceConstants(parameterString, minecart);

                                        if (info.getTypeArray().hasNumberType()) {
                                            parameterString = generateJavaScript(parameterString);
                                            try {
                                                ScriptEngine scriptEngine = SCRIPT_ENGINE_MANAGER.getEngineByName("JavaScript");
                                                scriptEngine.eval("var result = " + String.valueOf(parameterString) + ";");
                                                parameterString = String.valueOf(scriptEngine.get("result"));
                                            } catch (ScriptException e) {
                                                ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Expression script error for: " + expressionPart));
                                                return;
                                            }
                                        }

                                        Object parameter = parameterString;
                                        if (info.getTypeArray().hasNumberType() && isNumber(String.valueOf(parameter))) {
                                            parameter = Double.parseDouble(String.valueOf(parameter));
                                        }

                                        expressionCommand.execute(minecart, parameter);
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String[] splitExpression(String expression) {

        for (int counter = 0; counter < expression.length(); counter++) {
            if (expression.charAt(counter) == ' ') {
                return new String[] { expression.substring(0, counter), expression.substring(counter + 1, expression.length()) };
            }
        }

        return new String[] { expression };
    }

    private String replaceConstants(String parameter, Minecart minecart) {

        for (ExpressionConstant expressionConstant : expressionConstants) {
            ExpressionConstantInfo info2 = expressionConstant.getInfo();

            String[] constantLabels = info2.getConstantLabels();
            Arrays.sort(constantLabels, new StringLengthComperator());
            List<String> temp = Arrays.asList(constantLabels);
            Collections.reverse(temp);
            constantLabels = temp.toArray(new String[constantLabels.length]);

            for (String constantLabel : constantLabels) {
                parameter = String.valueOf(parameter).replaceAll("\\$" + constantLabel, String.valueOf(expressionConstant.getValue(minecart)));
            }
        }

        return parameter;
    }

    private String generateJavaScript(String string) {

        String stringMark = "\"";
        String javaScript = "";
        boolean stringOpen = false;

        if (!isMathSymbol(string.charAt(0))) {
            javaScript += stringMark + stringMark;
        }

        for (int counter = 0; counter < string.length(); counter++) {
            char c = string.charAt(counter);

            if (isMathSymbol(c) || isOperator(c)) {
                if (stringOpen) {
                    javaScript += stringMark + " + " + c;
                    stringOpen = false;
                } else {
                    javaScript += c;
                }
            } else if (c == ' ') {
                if (stringOpen) {
                    javaScript += c;
                }
            } else {
                if (stringOpen) {
                    javaScript += c;
                } else {
                    int counter2 = counter - 1;
                    while (counter2 >= 0 && string.charAt(counter2) == ' ') {
                        counter2--;
                    }
                    counter2++;
                    if (counter2 < 0) {
                        counter2 = 0;
                    }

                    javaScript += " + " + stringMark + string.substring(counter2, counter + 1);
                    stringOpen = true;
                }
            }
        }

        if (stringOpen) {
            javaScript += stringMark;
        }

        return javaScript;
    }

    private boolean isNumber(String string) {

        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isMathSymbol(char c) {

        return isNumber(String.valueOf(c)) || c == '.' || c == '(' || c == ')';
    }

    private boolean isOperator(char c) {

        for (char operator : OPERATORS) {
            if (c == operator) {
                return true;
            }
        }

        return false;
    }

}
