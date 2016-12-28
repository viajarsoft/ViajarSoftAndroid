package com.app.viajarsoft.ventatiquetesdomain.business_models;

import java.io.Serializable;

/**
 * Created by jose on 28/12/16.
 */

public class TipoBus implements Serializable {

    private String tipo;
    private String descripcion;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
