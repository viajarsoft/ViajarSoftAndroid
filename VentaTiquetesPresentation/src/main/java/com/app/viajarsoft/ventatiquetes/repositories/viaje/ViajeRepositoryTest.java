package com.app.viajarsoft.ventatiquetes.repositories.viaje;

import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;

/**
 * Created by jose on 27/12/16.
 */

public class ViajeRepositoryTest implements IViajeRepository {
    @Override
    public BussesAndRoutes getBussesAndRoutes(String codigoOficina) throws RepositoryError {
        return null;
    }
}
