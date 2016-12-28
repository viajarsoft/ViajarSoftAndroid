package com.app.viajarsoft.ventatiquetesdomain.business_models;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class Viaje {

    private String codigoTipoBus;
    private String codigoRuta;
    private String codigoTipoPasaje;

    public String getCodigoTipoBus() {
        return codigoTipoBus;
    }

    public void setCodigoTipoBus(String codigoTipoBus) {
        this.codigoTipoBus = codigoTipoBus;
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public void setCodigoTipoPasaje(String codigoTipoPasaje) {
        this.codigoTipoPasaje = codigoTipoPasaje;
    }

    public String getCodigoTipoPasaje() {
        return codigoTipoPasaje;
    }
}
