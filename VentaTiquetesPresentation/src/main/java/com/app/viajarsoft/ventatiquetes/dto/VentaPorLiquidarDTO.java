package com.app.viajarsoft.ventatiquetes.dto;

import java.text.DecimalFormat;

/**
 * Created by USUARIO on 2/01/2017.
 */

public class VentaPorLiquidarDTO {

    private String CodigoOficina;
    private String CodigoTaquilla;
    private String CodigoTipoTiquete;
    private String FechaVenta;
    private int Cantidad;
    private Double ValorTiquete;
    private Double ValorSeguro;
    private String NombreTaquilla;

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

    public String getCodigoTipoTiquete() {
        return CodigoTipoTiquete;
    }

    public void setCodigoTipoTiquete(String codigoTipoTiquete) {
        CodigoTipoTiquete = codigoTipoTiquete;
    }

    public String getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        FechaVenta = fechaVenta;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Double getValorTiquete() {
        return ValorTiquete;
    }

    public void setValorTiquete(Double valorTiquete) {
        ValorTiquete = valorTiquete;
    }

    public Double getValorSeguro() {
        return ValorSeguro;
    }

    public void setValorSeguro(Double valorSeguro) {
        ValorSeguro = valorSeguro;
    }

    public String getNombreTaquilla() {
        return NombreTaquilla;
    }

    public void setNombreTaquilla(String nombreTaquilla) {
        NombreTaquilla = nombreTaquilla;
    }
}
