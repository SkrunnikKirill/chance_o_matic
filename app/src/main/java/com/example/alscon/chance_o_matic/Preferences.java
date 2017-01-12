package com.example.alscon.chance_o_matic;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alscon on 05-Jan-17.
 */

public class Preferences {
    SharedPreferences sharedPreferences;
    Context context;


    public Preferences(Context context) {
        this.context = context;

    }

    public void save(Integer userId, String constant) {
        sharedPreferences = context.getSharedPreferences(constant, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putInt(constant, userId);
        ed.commit();

    }

    public Integer load(String constant) {
        Integer savedText = null;
        sharedPreferences = context.getSharedPreferences(constant, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(constant)) {
            savedText = sharedPreferences.getInt(constant, 0);

        }


        return savedText;
    }

    public void remove(String constant) {
        sharedPreferences = context.getSharedPreferences(constant, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }
}
