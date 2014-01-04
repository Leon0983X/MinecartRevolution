/*
 * This file is part of MinecartRevolution-Basicexpressions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicexpressions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicexpressions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicexpressions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicexpressions.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.conf.FileConf;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.ExpressionExecutor;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public class FileCommand extends ExpressionCommand {

    public FileCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING, Type.DOUBLE), "f", "file");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return true;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        File file = new File(FileConf.SCRIPTS, String.valueOf(parameter));
        if (!file.exists()) {
            file = new File(FileConf.SCRIPTS, String.valueOf(parameter) + ".revo");
        }
        if (!file.exists()) {
            file = new File(FileConf.SCRIPTS, String.valueOf(parameter) + ".txt");
        }

        try {
            if (file.exists()) {
                List<String> lines = new ArrayList<String>();

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ( (line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                minecartRevolution.getExpressionExecutor().execute(minecart, ExpressionExecutor.getExpression(lines.toArray(new String[lines.size()])));
            }
        }
        catch (Exception e) {
            ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Failed to load script file " + file.getPath()));
        }
    }

}
