
package com.quartercode.basicexpression.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.expression.MRExpressionExecutor;
import com.quartercode.qcutil.io.File;

public class FileCommand extends ExpressionCommand {

    private final MRExpressionExecutor expressionExecutor;

    public FileCommand(final MRExpressionExecutor expressionExecutor) {

        this.expressionExecutor = expressionExecutor;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING, Type.DOUBLE), "f", "file");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        File file = new File(FileConf.SCRIPTS, String.valueOf(parameter));
        if (!file.exists()) {
            file = new File(FileConf.SCRIPTS, String.valueOf(parameter) + ".revo");
        }
        if (!file.exists()) {
            file = new File(FileConf.SCRIPTS, String.valueOf(parameter) + ".txt");
        }

        try {
            if (file.exists()) {
                final List<String> lines = new ArrayList<String>();

                final BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ( (line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                expressionExecutor.execute(minecart, MRExpressionExecutor.getExpression(lines.toArray(new String[lines.size()])));
            }
        }
        catch (final Exception e) {
            MinecartRevolution.handleSilenceThrowable(e);
        }
    }

}
