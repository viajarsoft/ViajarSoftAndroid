package com.app.viajarsoft.ventatiquetes.dto;

/**
 * Created by jose on 19/12/16.
 */

public class UsuarioRequestDTO {

    private String Usuario;
    private String Clave;
    private String IpUsuario;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        this.Clave = clave;
    }

    public String getIpUsuario() {
        return IpUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        IpUsuario = ipUsuario;
    }
}
