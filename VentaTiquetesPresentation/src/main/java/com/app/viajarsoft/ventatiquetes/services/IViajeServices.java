package com.app.viajarsoft.ventatiquetes.services;

import com.app.viajarsoft.ventatiquetes.dto.BussesAndRoutesDTO;
import com.app.viajarsoft.ventatiquetes.dto.ListDestinationPricesDTO;
import com.app.viajarsoft.ventatiquetes.dto.ListTipoTiquetesDTO;
import com.app.viajarsoft.ventatiquetes.dto.TiqueteDTO;
import com.app.viajarsoft.ventatiquetes.dto.ViajeDTO;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by jose on 27/12/16.
 */

public interface IViajeServices {

    @GET("/Factura/ObtenerRutas")
    BussesAndRoutesDTO getBussesAndRoutes(@Query("codigoOficinaOrigen") String codigoOficina);

    @POST("/Factura/ObtenerTiposTiquete")
    ListTipoTiquetesDTO getTickets(@Body ViajeDTO viajeDTO);

    @POST("/Factura/ObtenerPreciosDestino")
    ListDestinationPricesDTO getDestinationPrices(@Body ViajeDTO viajeDTO);

    @POST("/Factura/VentaTiquete")
    TiqueteDTO sellTicket(@Body ViajeDTO viajeDTO);
}
