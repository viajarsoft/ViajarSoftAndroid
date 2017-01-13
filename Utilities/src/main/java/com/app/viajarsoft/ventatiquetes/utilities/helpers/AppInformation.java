package com.app.viajarsoft.ventatiquetes.utilities.helpers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by josetabaresramirez on 13/01/17.
 */

public class AppInformation implements IAppInformation {

    private Context context;

    public AppInformation(Context context) {
        this.context = context;
    }

    @Override
    public String getAppVersion() {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return "Versión " + pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Versión 1.0.0";
        }
    }
}
