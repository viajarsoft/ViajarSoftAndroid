package com.app.viajarsoft.ventatiquetesdomain.viaje;

import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DestinationPrice;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Tiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;

import java.util.List;

/**
 * Created by jose on 27/12/16.
 */

public interface IViajeRepository {

    BussesAndRoutes getBussesAndRoutes(String codigoOficina) throws RepositoryError;

    List<TipoTiquete> getTickets(Viaje viaje) throws RepositoryError;

    List<DestinationPrice> getDestinationPrices(Viaje viaje) throws RepositoryError;

    Tiquete sellTicket(Viaje viaje) throws RepositoryError;

    VentaPorLiquidar getSummaryLiquidation(ResumenLiquidacion resumenLiquidacion) throws RepositoryError;
}
