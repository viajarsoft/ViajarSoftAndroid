package com.app.viajarsoft.ventatiquetes.dto;

/**
 * Created by USUARIO on 4/01/2017.
 */

public class LiquidacionDTO {

    private String CodigoOficina;
    private String CodigoTaquilla;
    private String TipoVenta;
    private String FechaVenta;
    private String CodigoUsuario;

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

    public String getTipoVenta() {
        return TipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        TipoVenta = tipoVenta;
    }

    public String getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        FechaVenta = fechaVenta;
    }

    public String getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        CodigoUsuario = codigoUsuario;
    }
}