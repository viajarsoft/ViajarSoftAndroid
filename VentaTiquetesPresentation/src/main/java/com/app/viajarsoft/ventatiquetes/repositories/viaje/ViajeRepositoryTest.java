package com.app.viajarsoft.ventatiquetes.repositories.viaje;

import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DestinationPrice;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Tiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;

import java.util.List;

/**
 * Created by jose on 27/12/16.
 */

public class ViajeRepositoryTest implements IViajeRepository {
    @Override
    public BussesAndRoutes getBussesAndRoutes(String codigoOficina) throws RepositoryError {
        return null;
    }

    @Override
    public List<TipoTiquete> getTickets(Viaje viaje) throws RepositoryError {
        return null;
    }

    @Override
    public List<DestinationPrice> getDestinationPrices(Viaje viaje) throws RepositoryError {
        return null;
    }

    @Override
    public Tiquete sellTicket(Viaje viaje) throws RepositoryError {
        return null;
    }

    @Override
    public VentaPorLiquidar getSummaryLiquidation(ResumenLiquidacion resumenLiquidacion) {
        return null;
    }
}
