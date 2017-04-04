package com.congnin.chat.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by congnc on 4/3/17.
 */

public class SharedPrefUtil {
    private static final String APP_PREFS = "application_preferences";

    private Context context;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SharedPrefUtil(Context mContext) {
        this.context = mContext;
    }

    public void saveString(String key, String value) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void saveInt(String key, int value) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public void saveBoolean(String key, boolean value) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public String getString(String key) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, defaultValue);
    }

    public int getInt(String key) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void clear() {
        mSharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        mSharedPreferences.edit().clear().apply();
    }
}
