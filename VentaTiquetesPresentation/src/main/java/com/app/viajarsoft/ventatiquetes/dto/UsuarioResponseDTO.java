package com.app.viajarsoft.ventatiquetes.dto;

/**
 * Created by josetabaresramirez on 18/11/16.
 */

public class UsuarioResponseDTO {

    private String Token;
    private String CodigoOficina;
    private String CodigoTaquilla;
    private String IdentificadorEmpresa;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getCodigoOficina() {
        return CodigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        CodigoOficina = codigoOficina;
    }

    public String getCodigoTaquilla() {
        return CodigoTaquilla;
    }

    public void setCodigoTaquilla(String codigoTaquilla) {
        CodigoTaquilla = codigoTaquilla;
    }

    public String getIdentificadorEmpresa() {
        return IdentificadorEmpresa;
    }

    public void setIdentificadorEmpresa(String identificadorEmpresa) {
        IdentificadorEmpresa = identificadorEmpresa;
    }
}
