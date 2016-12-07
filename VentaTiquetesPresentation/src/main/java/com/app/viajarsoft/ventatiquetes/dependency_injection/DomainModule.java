package com.app.viajarsoft.ventatiquetes.dependency_injection;

import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetesdomain.security.SecurityBL;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class DomainModule {
    public static SecurityBL getSecurityBLInstance(CustomSharedPreferences customSharedPreferences) {
        return new SecurityBL(RepositoryLocator.getSecurityRepositoryInstance(customSharedPreferences));
    }
}
