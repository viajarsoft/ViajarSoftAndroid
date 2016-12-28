package com.app.viajarsoft.ventatiquetesdomain.viaje;

import com.app.viajarsoft.ventatiquetes.utilities.helpers.Validations;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;

/**
 * Created by jose on 27/12/16.
 */

public class ViajeBL {

    private IViajeRepository viajeRepository;

    public ViajeBL(IViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    public BussesAndRoutes getBussesAndRoutes(String codigoOficina) throws RepositoryError {
        Validations.validateNullParameter(codigoOficina);
        Validations.validateEmptyParameter(codigoOficina);
        return viajeRepository.getBussesAndRoutes(codigoOficina);
    }
}
