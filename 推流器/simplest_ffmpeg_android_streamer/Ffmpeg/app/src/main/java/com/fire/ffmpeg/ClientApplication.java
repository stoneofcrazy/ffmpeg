package com.fire.ffmpeg;

import android.app.Application;

import com.fire.ffmpeg.utils.FileUtils;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ClientApplication extends Application {
    private static ClientApplication sApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        FileUtils.init(getApplicationContext());
    }

    public static Application getApplication() {
        return sApplication;
    }

}
