package com.jade.library;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jade.library.config.FrescoConfig;

/**
 * Created by jade on 16-8-2.
 */
public class Configuration {

    private final Application mApp;
    private final Context mContext;
    private final String mLogPath;

    private Configuration(final Builder builder) {
        mApp = builder.app;
        mContext = builder.context;
        mLogPath = builder.logPath;
    }

    public static class Builder {
        private Context context;
        private Application app;
        private String logPath;

        public Builder(Application application) {
            this.app = application;
            this.context = app.getApplicationContext();
        }

        public Builder initFresco() {
            Fresco.initialize(app.getApplicationContext(), FrescoConfig.getImagePipelineConfig(context));
            return this;
        }

        public Builder initLogPath(String path) {
            this.logPath = path;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }

    public String getLogPath() {
        return mLogPath;
    }

    public Application getApp() {
        return mApp;
    }

    public Context getContext() {
        return mContext;
    }
}
