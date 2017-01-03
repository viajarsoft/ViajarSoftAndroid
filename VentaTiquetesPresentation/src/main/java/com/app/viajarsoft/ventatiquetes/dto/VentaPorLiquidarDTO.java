package com.app.viajarsoft.ventatiquetes.dto;

import java.text.DecimalFormat;

/**
 * Created by USUARIO on 2/01/2017.
 */

public class VentaPorLiquidarDTO {

    private String codigoOficina;
    private String codigoTaquilla;
    private String codigoTipoTiquete;
    private String fechaVenta;
    private int cantidad;
    private DecimalFormat valorTiquete;
    private DecimalFormat valorSeguro;

    public DecimalFormat getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(DecimalFormat valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public String getCodigoTaquilla() {
        return codigoTaquilla;
    }

    public void setCodigoTaquilla(String codigoTaquilla) {
        this.codigoTaquilla = codigoTaquilla;
    }

    public String getCodigoTipoTiquete() {
        return codigoTipoTiquete;
    }

    public void setCodigoTipoTiquete(String codigoTipoTiquete) {
        this.codigoTipoTiquete = codigoTipoTiquete;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DecimalFormat getValorTiquete() {
        return valorTiquete;
    }

    public void setValorTiquete(DecimalFormat valorTiquete) {
        this.valorTiquete = valorTiquete;
    }

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }
}
