package com.example.alscon.ball;

import android.app.Application;

/**
 * Created by Alscon on 15-Dec-16.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
