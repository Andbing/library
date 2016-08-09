package com.jade.library;

/**
 * Created by jade on 16-8-2.
 */
public class LibManager {
    private static LibManager ourInstance = new LibManager();
    private Configuration config;

    public static LibManager getInstance() {
        return ourInstance;
    }

    private LibManager() {
    }

    public void init(Configuration cfg){
        this.config = cfg;
    }

    public Configuration getConfig() {
        return config;
    }
}
