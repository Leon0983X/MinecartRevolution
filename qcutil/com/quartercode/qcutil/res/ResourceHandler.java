
package com.quartercode.qcutil.res;

public class ResourceHandler {

    protected ResourceManager resourceManager;

    public ResourceHandler(ResourceManager resourceManager) {

        setResourceManager(resourceManager);
    }

    public ResourceManager getResourceManager() {

        return resourceManager;
    }

    public void setResourceManager(ResourceManager resourceManager) {

        this.resourceManager = resourceManager;
    }

}
