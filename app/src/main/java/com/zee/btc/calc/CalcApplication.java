package com.zee.btc.calc;

import android.app.Application;

import com.zee.utils.ZLibrary;

public class CalcApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ZLibrary.init(this, true);
    }
}
