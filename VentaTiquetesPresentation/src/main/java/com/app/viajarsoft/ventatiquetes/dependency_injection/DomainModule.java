package com.app.viajarsoft.ventatiquetes.dependency_injection;

import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetesdomain.security.SecurityBL;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class DomainModule {
    public static SecurityBL getSecurityBLInstance(ICustomSharedPreferences customSharedPreferences) {
        return new SecurityBL(RepositoryLocator.getSecurityRepositoryInstance(customSharedPreferences));
    }

    public static ViajeBL getViajeBLInstance(ICustomSharedPreferences customSharedPreferences) {
        return new ViajeBL(RepositoryLocator.getViajeRepositoryInstance(customSharedPreferences));
    }
}
