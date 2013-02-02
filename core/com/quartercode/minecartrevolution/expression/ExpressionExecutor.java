
package com.quartercode.minecartrevolution.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.ScriptException;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionSilenceException;
import com.quartercode.qcutil.script.ScriptExecutor;
import com.quartercode.quarterbukkit.QuarterBukkit;

public class ExpressionExecutor {

    private static final String COMMAND_END_MARKER = ";";
    private static final char[] OPERATORS          = { '+', '-', '*', '/' };

    public static String getExpression(final String[] lines) {

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

    public ExpressionExecutor(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;

        setExpressionCommands(new ArrayList<ExpressionCommand>());
        setExpressionConstants(new ArrayList<ExpressionConstant>());
        minecartExpressions = new HashMap<Minecart, List<String>>();
    }

    public List<ExpressionCommand> getExpressionCommands() {

        return Collections.unmodifiableList(expressionCommands);
    }

    public void setExpressionCommands(final List<ExpressionCommand> expressionCommands) {

        this.expressionCommands = expressionCommands;
    }

    public void addExpressionCommand(final ExpressionCommand expressionCommand) {

        expressionCommand.setMinecartRevolution(minecartRevolution);
        expressionCommands.add(expressionCommand);
        expressionCommand.enable();
    }

    public List<ExpressionConstant> getExpressionConstants() {

        return Collections.unmodifiableList(expressionConstants);
    }

    public void setExpressionConstants(final List<ExpressionConstant> expressionConstants) {

        this.expressionConstants = expressionConstants;
    }

    public void addExpressionConstant(final ExpressionConstant expressionConstant) {

        expressionConstant.setMinecartRevolution(minecartRevolution);
        expressionConstants.add(expressionConstant);
        expressionConstant.enable();
    }

    public Map<Minecart, List<String>> getMinecartExpressions() {

        return Collections.unmodifiableMap(minecartExpressions);
    }

    public void execute(final Minecart minecart, String expression) {

        expression = expression.trim();
        final String[] expressionParts = expression.split(COMMAND_END_MARKER);

        for (final String expressionPart : expressionParts) {
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
                    final String removalExpressionPart = expressionPart.replaceAll("\\- ", "").replaceAll("remove ", "");
                    if (removalExpressionPart.split(" ").length == 1) {
                        for (final String minecartExpression : new ArrayList<String>(minecartExpressions.get(minecart))) {
                            if (minecartExpression.startsWith(removalExpressionPart)) {
	minecartExpressions.get(minecart).remove(minecartExpression);
                            }
                        }
                    } else {
                        for (final String minecartExpression : new ArrayList<String>(minecartExpressions.get(minecart))) {
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
                final String[] splittedExpressionPart = splitExpression(expressionPart);

                for (final ExpressionCommand expressionCommand : expressionCommands) {
                    final String command = splittedExpressionPart[0];

                    if (command != null) {
                        final ExpressionCommandInfo info = expressionCommand.getInfo();

                        for (final String ecCommandLabel : info.getCommandLabels()) {
                            if (ecCommandLabel.equalsIgnoreCase(command)) {
	if (expressionCommand.canExecute(minecart)) {
	    if (splittedExpressionPart.length == 1 && info.getTypeArray().isInstance(null)) {
	        expressionCommand.execute(minecart, null);
	        continue;
	    } else if (splittedExpressionPart.length == 2) {
	        String parameterString = splittedExpressionPart[1];
	        parameterString = replaceConstants(parameterString, minecart);

	        if (info.getTypeArray().isOneNumber()) {
	            parameterString = generateJavaScript(parameterString);
	            try {
	                final ScriptExecutor scriptExecutor = new ScriptExecutor("JavaScript");
	                scriptExecutor.execute("var result = " + String.valueOf(parameterString) + ";");
	                parameterString = String.valueOf(scriptExecutor.get("result"));
	            }
	            catch (final ScriptException e) {
	                QuarterBukkit.exception(new MinecartRevolutionSilenceException(minecartRevolution, e, "Expression script error for: " + expressionPart));
	                return;
	            }
	        }

	        Object parameter = parameterString;
	        if (info.getTypeArray().isOneNumber() && isNumber(String.valueOf(parameter))) {
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

    private String[] splitExpression(final String expression) {

        for (int counter = 0; counter < expression.length(); counter++) {
            if (expression.charAt(counter) == ' ') {
                return new String[] { expression.substring(0, counter), expression.substring(counter + 1, expression.length()) };
            }
        }

        return new String[] { expression };
    }

    private String replaceConstants(String parameter, final Minecart minecart) {

        for (final ExpressionConstant expressionConstant : expressionConstants) {
            final ExpressionConstantInfo info2 = expressionConstant.getInfo();

            String[] constantLabels = info2.getConstantLabels();
            Arrays.sort(constantLabels, new StringLengthComperator());
            final List<String> temp = Arrays.asList(constantLabels);
            Collections.reverse(temp);
            constantLabels = temp.toArray(new String[constantLabels.length]);

            for (final String constantLabel : constantLabels) {
                parameter = String.valueOf(parameter).replaceAll("\\$" + constantLabel, String.valueOf(expressionConstant.getValue(minecart)));
            }
        }

        return parameter;
    }

    private String generateJavaScript(final String string) {

        final String stringMark = "\"";
        String javaScript = "";
        boolean stringOpen = false;

        if (!isMathSymbol(string.charAt(0))) {
            javaScript += stringMark + stringMark;
        }

        for (int counter = 0; counter < string.length(); counter++) {
            final char c = string.charAt(counter);

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

    private boolean isNumber(final String string) {

        try {
            Double.parseDouble(string);
            return true;
        }
        catch (final NumberFormatException e) {
            return false;
        }
    }

    private boolean isMathSymbol(final char c) {

        return isNumber(String.valueOf(c)) || c == '.' || c == '(' || c == ')';
    }

    private boolean isOperator(final char c) {

        for (final char operator : OPERATORS) {
            if (c == operator) {
                return true;
            }
        }

        return false;
    }

}
