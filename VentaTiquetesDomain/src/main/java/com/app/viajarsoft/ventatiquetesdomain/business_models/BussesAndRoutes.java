package com.app.viajarsoft.ventatiquetesdomain.business_models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jose on 27/12/16.
 */

public class BussesAndRoutes implements Serializable {

    private ArrayList<Ruta> rutas;
    private ArrayList<TipoBus> tiposBuses;

    public ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
    }

    public ArrayList<TipoBus> getTiposBuses() {
        return tiposBuses;
    }

    public void setTiposBuses(ArrayList<TipoBus> tiposBuses) {
        this.tiposBuses = tiposBuses;
    }
}
