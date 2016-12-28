package com.app.viajarsoft.ventatiquetes.services;

import com.app.viajarsoft.ventatiquetes.dto.BussesAndRoutesDTO;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jose on 27/12/16.
 */

public interface IViajeServices {

    @GET("/Factura/ObtenerRutas")
    BussesAndRoutesDTO getBussesAndRoutes(@Query("codigoOficinaOrigen") String codigoOficina);
}
