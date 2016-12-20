package com.app.viajarsoft.ventatiquetesdomain.business_models;

import java.io.Serializable;

/**
 * Created by jose on 19/12/16.
 */

public class UsuarioResponse implements Serializable{

    private String token;
    private String codigoOficina;
    private String codigoTaquilla;
    private String identificadorEmpresa;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }

    public String getCodigoTaquilla() {
        return codigoTaquilla;
    }

    public void setCodigoTaquilla(String codigoTaquilla) {
        this.codigoTaquilla = codigoTaquilla;
    }

    public String getIdentificadorEmpresa() {
        return identificadorEmpresa;
    }

    public void setIdentificadorEmpresa(String identificadorEmpresa) {
        this.identificadorEmpresa = identificadorEmpresa;
    }
}
