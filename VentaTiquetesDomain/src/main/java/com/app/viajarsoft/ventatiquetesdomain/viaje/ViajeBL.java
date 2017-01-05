package com.app.viajarsoft.ventatiquetesdomain.viaje;

import com.app.viajarsoft.ventatiquetes.utilities.helpers.Validations;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DestinationPrice;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Liquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacionImpresion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenVentasPorLiquidar;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Tiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;
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

    public List<DestinationPrice> getDestinationPrices(Viaje viaje) throws RepositoryError {

        Validations.validateNullParameter(viaje);
        Validations.validateNullParameter(viaje.getTipoTiquete());
        Validations.validateEmptyParameter(viaje.getTipoTiquete());

        return viajeRepository.getDestinationPrices(viaje);
    }

    public Tiquete sellTicket(Viaje viaje) throws RepositoryError {
        return viajeRepository.sellTicket(viaje);
    }


    public ResumenVentasPorLiquidar getSummaryLiquidation(ResumenLiquidacion resumenLiquidacion) throws RepositoryError {

        Validations.validateNullParameter(resumenLiquidacion.getCodigoOficina(), resumenLiquidacion.getCodigoTaquilla());
        Validations.validateEmptyParameter(resumenLiquidacion.getCodigoOficina(), resumenLiquidacion.getCodigoTaquilla());
        return viajeRepository.getSummaryLiquidation(resumenLiquidacion);
    }

    public ResumenLiquidacionImpresion getLiquidation(Liquidacion liquidacion) throws RepositoryError{
        Validations.validateNullParameter(liquidacion);
        Validations.validateEmptyParameter(liquidacion.getCodigoOficina(), liquidacion.getCodigoTaquilla(), liquidacion.getCodigoUsuario(), liquidacion.getFechaVenta());
        Validations.validateNullParameter(liquidacion.getCodigoOficina(), liquidacion.getCodigoTaquilla(), liquidacion.getCodigoUsuario(), liquidacion.getFechaVenta());
        return viajeRepository.getLiquidation(liquidacion);
    }
}
