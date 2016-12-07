package com.app.viajarsoft.ventatiquetes.utilities.helpers;


import android.content.Context;
import android.content.SharedPreferences;

import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;


/**
 * Created by JoseTabares on 13/05/16.
 */
public class CustomSharedPreferences implements ICustomSharedPreferences {

    private SharedPreferences sharedPreferences;

    public CustomSharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(IConstants.MY_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public String getString(String key) {
        if (sharedPreferences.contains(key)) {
            return sharedPreferences.getString(key, null);
        }
        return null;
    }

    @Override
    public void addString(String key, String value) {
        if (value == null) {
            deleteValue(key);
        } else {
            addValue(key, value);
        }
    }

    @Override
    public void deleteValue(String key) {
        sharedPreferences.edit().remove(key).commit();
    }

    private void addValue(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }
}
