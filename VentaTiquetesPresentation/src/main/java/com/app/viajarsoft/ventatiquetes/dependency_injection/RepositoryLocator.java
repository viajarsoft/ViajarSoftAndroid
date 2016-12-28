package com.app.viajarsoft.ventatiquetes.dependency_injection;

import com.app.viajarsoft.ventatiquetes.repositories.security.SecurityRepository;
import com.app.viajarsoft.ventatiquetes.repositories.security.SecurityRepositoryTest;
import com.app.viajarsoft.ventatiquetes.repositories.viaje.ViajeRepository;
import com.app.viajarsoft.ventatiquetes.repositories.viaje.ViajeRepositoryTest;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetesdomain.security.ISecurityRepository;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

class RepositoryLocator {

    static ISecurityRepository getSecurityRepositoryInstance(ICustomSharedPreferences customSharedPreferences) {
        if (IConstants.IS_DEBUG) {
            return new SecurityRepositoryTest();
        } else {
            return new SecurityRepository(customSharedPreferences);
        }
    }

    static IViajeRepository getViajeRepositoryInstance(ICustomSharedPreferences customSharedPreferences) {
        if (IConstants.IS_DEBUG) {
            return new ViajeRepositoryTest();
        } else {
            return new ViajeRepository(customSharedPreferences);
        }
    }
}
