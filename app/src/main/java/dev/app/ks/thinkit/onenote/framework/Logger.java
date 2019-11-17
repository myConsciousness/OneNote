package dev.app.ks.thinkit.onenote.framework;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Logger {
    Info {
        @Override
        public void write(String tag, String methodName, String message, String... replaceArgs) {

            write(tag, methodName, message, getReplaceList(replaceArgs));
        }

        @Override
        public void write(String tag, String methodName, String message, List<String> replaceList) {

            Log.i(tag, formatMessage(methodName, message, replaceList));
        }
    },

    Debug {
        @Override
        public void write(String tag, String methodName, String message, String... replaceArgs) {

            write(tag, methodName, message, getReplaceList(replaceArgs));
        }

        @Override
        public void write(String tag, String methodName, String message, List<String> replaceList) {

            Log.d(tag, formatMessage(methodName, message, replaceList));
        }
    },

    Warn {
        @Override
        public void write(String tag, String methodName, String message, String... replaceArgs) {

            write(tag, methodName, message, getReplaceList(replaceArgs));
        }

        @Override
        public void write(String tag, String methodName, String message, List<String> replaceList) {

            Log.w(tag, formatMessage(methodName, message, replaceList));
        }
    },

    Error {
        @Override
        public void write(String tag, String methodName, String message, String... replaceArgs) {

            write(tag, methodName, message, getReplaceList(replaceArgs));
        }

        @Override
        public void write(String tag, String methodName, String message, List<String> replaceList) {

            Log.e(tag, formatMessage(methodName, message, replaceList));
        }
    };

    private static final String LOG_FORMAT = "%s [%s] :: %s";

    Logger() {
    }

    protected String formatMessage(String methodName, String message, List<String> replaceList) {

        String replacedMessage = replaceList.isEmpty() ? message : String.format(message, replaceList);

        return String.format(LOG_FORMAT, name(), methodName, replacedMessage);
    }

    protected List<String> getReplaceList(String[] replaceArgs) {

        List<String> replaceList = new ArrayList<>();

        if (replaceArgs.length > 0) {
            Arrays.asList(replaceArgs)
                    .stream()
                    .forEach(arg -> replaceList.add(arg));
        }

        return replaceList;
    }

    public abstract void write(String tag, String methodName, String message, String... replaceArgs);

    public abstract void write(String tag, String methodName, String message, List<String> replaceList);
}
