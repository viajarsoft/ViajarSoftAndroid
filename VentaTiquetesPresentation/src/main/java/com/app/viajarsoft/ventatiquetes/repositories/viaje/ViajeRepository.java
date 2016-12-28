package com.app.viajarsoft.ventatiquetes.repositories.viaje;

import com.app.viajarsoft.ventatiquetes.dto.BussesAndRoutesDTO;
import com.app.viajarsoft.ventatiquetes.helpers.Mapper;
import com.app.viajarsoft.ventatiquetes.services.IViajeServices;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.services.ServicesFactory;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;

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
}
