package hu.ait.javademoapp.staticmethods;

import android.util.Log;

public class Logger {
    private static final String TAG = "StopWatch";

    public static void log(String msg) {
        Log.i(TAG, msg);
    }
}

