package com.app.viajarsoft.ventatiquetesdomain.viaje;

import com.app.viajarsoft.ventatiquetes.utilities.helpers.Validations;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;

import java.util.List;

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

    public List<TipoTiquete> getTickets(Viaje viaje) throws RepositoryError {

        Validations.validateNullParameter(viaje);
        Validations.validateNullParameter(viaje.getCodigoRuta(), viaje.getCodigoTipoBus());
        Validations.validateEmptyParameter(viaje.getCodigoRuta(), viaje.getCodigoTipoBus());

        return viajeRepository.getTickets(viaje);
    }
}
