package com.app.viajarsoft.ventatiquetes.dto;

/**
 * Created by josetabaresramirez on 18/11/16.
 */

public class UsuarioResponseDTO {

    private MensajeDTO Mensaje;
    private UsuarioDTO Usuario;

    public MensajeDTO getMensaje() {
        return Mensaje;
    }

    public void setMensaje(MensajeDTO mensaje) {
        Mensaje = mensaje;
    }

    public UsuarioDTO getUsuario() {
        return Usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        Usuario = usuario;
    }
}
