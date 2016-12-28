package com.app.viajarsoft.ventatiquetesdomain.viaje;

import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;

/**
 * Created by jose on 27/12/16.
 */

public interface IViajeRepository {
    BussesAndRoutes getBussesAndRoutes(String codigoOficina) throws RepositoryError;
}
