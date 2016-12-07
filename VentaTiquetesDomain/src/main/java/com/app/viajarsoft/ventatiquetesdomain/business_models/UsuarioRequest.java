package com.app.viajarsoft.ventatiquetesdomain.business_models;

/**
 * Created by josetabaresramirez on 16/11/16.
 */

public class UsuarioRequest {

    private String correoElectronico;
    private String contrasenia;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
