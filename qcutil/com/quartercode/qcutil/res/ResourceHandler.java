
package com.quartercode.qcutil.res;

public class ResourceHandler {

    protected ResourceManager resourceManager;

    public ResourceHandler(final ResourceManager resourceManager) {

        setResourceManager(resourceManager);
    }

    public ResourceManager getResourceManager() {

        return resourceManager;
    }

    public void setResourceManager(final ResourceManager resourceManager) {

        this.resourceManager = resourceManager;
    }

}
