package com.app.viajarsoft.ventatiquetes.repositories.viaje;

import com.app.viajarsoft.ventatiquetes.dto.BussesAndRoutesDTO;
import com.app.viajarsoft.ventatiquetes.dto.ListDestinationPricesDTO;
import com.app.viajarsoft.ventatiquetes.dto.ListTipoTiquetesDTO;
import com.app.viajarsoft.ventatiquetes.dto.ViajeDTO;
import com.app.viajarsoft.ventatiquetes.helpers.Mapper;
import com.app.viajarsoft.ventatiquetes.services.IViajeServices;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.services.ServicesFactory;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DestinationPrice;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;

import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by jose on 27/12/16.
 */

public class ViajeRepository implements IViajeRepository {

    private IViajeServices viajeServices;

    public ViajeRepository(ICustomSharedPreferences customSharedPreferences) {
        ServicesFactory servicesFactory = new ServicesFactory(customSharedPreferences);
        viajeServices = (IViajeServices) servicesFactory.getInstance(IViajeServices.class);
    }

    @Override
    public BussesAndRoutes getBussesAndRoutes(String codigoOficina) throws RepositoryError {
        try {
            BussesAndRoutesDTO bussesAndRoutesDTO = viajeServices.getBussesAndRoutes(codigoOficina);
            return Mapper.convertBussesAndRoutesDTOToDomain(bussesAndRoutesDTO);
        } catch (RetrofitError retrofitError) {
            throw Mapper.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

    @Override
    public List<TipoTiquete> getTickets(Viaje viaje) throws RepositoryError {
        try {
            ViajeDTO viajeDTO = Mapper.convertViajeDomainToDTO(viaje);
            ListTipoTiquetesDTO listTipoTiquetesDTO = viajeServices.getTickets(viajeDTO);
            return Mapper.convertListTipoTiquetesDTOToDomain(listTipoTiquetesDTO);
        } catch (RetrofitError retrofitError) {
            throw Mapper.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

    @Override
    public List<DestinationPrice> getDestinationPrices(Viaje viaje) throws RepositoryError {
        try {
            ViajeDTO viajeDTO = Mapper.convertViajeDomainToDTO(viaje);
            ListDestinationPricesDTO listDestinationPricesDTO = viajeServices.getDestinationPrices(viajeDTO);
            return Mapper.convertListDestinationPricesDTOToDomain(listDestinationPricesDTO);
        } catch (RetrofitError retrofitError) {
            throw Mapper.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }
}
