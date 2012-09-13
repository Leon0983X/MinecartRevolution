
package com.quartercode.qcutil.res;

import com.quartercode.qcutil.QcUtil;
import com.quartercode.qcutil.io.File;
import com.quartercode.qcutil.io.Properties;

public class PropertyResourceHandler extends ResourceHandler {

    public PropertyResourceHandler(ResourceManager resourceManager) {

        super(resourceManager);
    }

    public String getProperty(String key, String... variables) {

        try {
            File resource = resourceManager.getResource();

            if (resource != null) {
                Properties resourceProperties = new Properties(resource);

                String result = resourceProperties.getProperty(key);
                if (result != null) {
                    for (int counter = 0; counter < variables.length; counter += 2) {
                        result = result.replaceAll("\\$" + variables[counter] + "\\$", variables[counter + 1]);
                    }
                }

                return result;
            }
        }
        catch (Throwable t) {
            QcUtil.handleThrowable(t);
        }

        return "";
    }
}
