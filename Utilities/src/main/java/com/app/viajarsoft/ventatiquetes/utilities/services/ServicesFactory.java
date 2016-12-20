package com.app.viajarsoft.ventatiquetes.utilities.services;

import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by JoseTabares on 13/05/16.
 *
 */
public class ServicesFactory {

    private static final String API_BASE_PATH = IConstants.URL_DLLO;
    private RestAdapter restAdapter;

    /**
     * Constructor, configura la petición http.
     *
     * @param customSharedPreferences SharedPreferences para obtener o agregar strings.
     */
    public ServicesFactory(ICustomSharedPreferences customSharedPreferences) {
        RequestInterceptor requestInterceptor = getRequestInterceptor(customSharedPreferences);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(IConstants.THIRTY, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(IConstants.THIRTY, TimeUnit.SECONDS);
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_BASE_PATH)
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient))
                .build();
    }

    private RequestInterceptor getRequestInterceptor(final ICustomSharedPreferences customSharedPreferences) {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade requestFacade) {
                requestFacade.addHeader("Accept", "application/json");
                String token = customSharedPreferences.getString(IConstants.TOKEN);
                String idSuscripcionOneSignal = customSharedPreferences.getString(IConstants.ID_SUSCRIPTION_ONE_SIGNAL);
                if (token != null) {
                    requestFacade.addHeader(IConstants.TOKEN, token);
                }
                if (idSuscripcionOneSignal != null) {
                    requestFacade.addHeader(IConstants.ID_SUSCRIPTION_ONE_SIGNAL, idSuscripcionOneSignal);
                }
            }
        };
    }

    /**
     * Obtiene la instancia del servicio.
     *
     * @param service Tipo de servicio.
     * @return Instancia del servicio.
     */
    public Object getInstance(Class service) {
        return restAdapter.create(service);
    }
}
