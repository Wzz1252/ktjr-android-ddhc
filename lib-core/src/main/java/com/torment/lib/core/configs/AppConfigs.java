package com.torment.lib.core.configs;

public class AppConfigs {

    private static AppConfigs sAppConfigs;

    private boolean mDebug = false;

    private AppConfigs() {
    }

    public static void init() {
        AppConfigs appConfigs = getInstance();
        appConfigs.setDebug(false);
    }

    public static AppConfigs getInstance() {
        if (sAppConfigs == null) {
            sAppConfigs = new AppConfigs();
        }
        return sAppConfigs;
    }

    public void setDebug(boolean debug) {
        this.mDebug = debug;
    }

    public boolean getDebug() {
        return mDebug;
    }
}
