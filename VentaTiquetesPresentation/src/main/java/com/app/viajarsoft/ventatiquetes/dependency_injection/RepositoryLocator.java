package com.app.viajarsoft.ventatiquetes.dependency_injection;

import com.app.viajarsoft.ventatiquetes.repositories.security.SecurityRepository;
import com.app.viajarsoft.ventatiquetes.repositories.security.SecurityRepositoryTest;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetesdomain.security.ISecurityRepository;

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
}
