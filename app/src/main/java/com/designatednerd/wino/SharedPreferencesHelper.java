package com.designatednerd.wino;

import android.content.Context;
import android.content.SharedPreferences;

import com.designatednerd.wino.model.WineTasting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Helper class to deal with accessing data in shared preferences.
 */
public class SharedPreferencesHelper {

    //Individual preference keys.
    private static final String STORED_TASTINGS  = "tastings";

    //The full application preference name.
    private static final String APP_PREFERENCE_NAME = "com.designatednerd.wino.sharedprefs";

    private static SharedPreferencesHelper sSharedInstance;
    private SharedPreferences mSharedPreferences;

    //A GSON adapter to help serialize/deserialize JSON
    private static final Gson PREFS_GSON = new GsonBuilder().create();

    /**
     * Designated initializer.
     *
     * @param aContext The current context
     */
    public static SharedPreferencesHelper getInstance(Context aContext) {
        //Create new if needed.
        if (sSharedInstance == null) {
            sSharedInstance = new SharedPreferencesHelper();

            //Grab the shared preferences directly so we don't need the context anymore.
            sSharedInstance.mSharedPreferences = aContext.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        }

        return sSharedInstance;
    }

    /**
     * @return The current SharedPreferences.Editor object
     */
    private SharedPreferences.Editor getSharedPreferencesEditor() {
        return mSharedPreferences.edit();
    }

    /**
     * Chokepoint method to reset all shared preferences to default values.
     */
    public void nukeAllSharedPreferences() {
        setCurrentTastings(null);
        writeAllToDisk();
    }

    public void writeAllToDisk() {
        getSharedPreferencesEditor().commit();
    }

    public void setCurrentTastings(List<WineTasting> aTastings) {
        SharedPreferences.Editor editor = getSharedPreferencesEditor();
        if (aTastings != null) {

            WineTasting[] tastingsArray = aTastings.toArray(new WineTasting[aTastings.size()]);
            String json = PREFS_GSON.toJson(tastingsArray);

            editor.putString(STORED_TASTINGS, json);
        } else {
            editor.remove(STORED_TASTINGS);
        }

        editor.apply();
    }

    public List<WineTasting> getCurrentTastings() {
        String json = mSharedPreferences.getString(STORED_TASTINGS, null);
        if (json != null ) {
            WineTasting[] tastings = PREFS_GSON.fromJson(json, WineTasting[].class);
            return Arrays.asList(tastings);
        } else {
            return null;
        }
    }
}
