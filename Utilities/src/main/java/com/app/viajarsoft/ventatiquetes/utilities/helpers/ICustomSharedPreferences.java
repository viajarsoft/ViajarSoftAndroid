package com.app.viajarsoft.ventatiquetes.utilities.helpers;

/**
 * Created by JoseTabares on 13/05/16.
 */
public interface ICustomSharedPreferences {

    String getString(String key);

    void addString(String key, String value);

    void deleteValue(String key);

}
