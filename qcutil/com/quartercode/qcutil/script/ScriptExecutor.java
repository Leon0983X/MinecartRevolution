
package com.quartercode.qcutil.script;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import com.quartercode.qcutil.io.File;
import com.quartercode.qcutil.utility.ObjectUtil;

public class ScriptExecutor {

    protected ScriptEngine scriptEngine;

    public ScriptExecutor(String language) throws ScriptException {

        initalize(language);
    }

    protected void initalize(String language) throws ScriptException {

        scriptEngine = new ScriptEngineManager().getEngineByName(language);
    }

    public ScriptEngine getEngine() {

        return scriptEngine;
    }

    public void set(String variable, Object value) {

        scriptEngine.put(variable, value);
    }

    public Object get(String variable) {

        return scriptEngine.get(variable);
    }

    public void importJavaPackage(String packageName) throws ScriptException {

        execute("importPackage(" + packageName + ");");
    }

    public void execute(String script) throws ScriptException {

        scriptEngine.eval(script);
    }

    public void execute(File scriptFile) throws ScriptException, FileNotFoundException, IOException {

        execute(scriptFile.read());
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (scriptEngine == null ? 0 : scriptEngine.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ScriptExecutor other = (ScriptExecutor) obj;
        if (scriptEngine == null) {
            if (other.scriptEngine != null) {
                return false;
            }
        } else if (!scriptEngine.equals(other.scriptEngine)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "scriptEngine");
    }
}
