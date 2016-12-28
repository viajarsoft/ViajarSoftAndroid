package com.app.viajarsoft.ventatiquetesdomain.business_models;

import java.io.Serializable;

/**
 * Created by jose on 28/12/16.
 */

public class Ruta implements Serializable {

    private String codigo;
    private String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
