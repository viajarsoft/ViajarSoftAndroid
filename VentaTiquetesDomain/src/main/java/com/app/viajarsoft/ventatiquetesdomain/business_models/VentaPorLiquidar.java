package com.app.viajarsoft.ventatiquetesdomain.business_models;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by USUARIO on 29/12/2016.
 */

public class VentaPorLiquidar implements Serializable {

    private String codigoOficina;
    private String codigoTaquilla;
    private String codigoTipoTiquete;
    private String fechaVenta;
    private int cantidad;
    private DecimalFormat valorTiquete;
    private DecimalFormat valorSeguro;

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }

    public DecimalFormat getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(DecimalFormat valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public DecimalFormat getValorTiquete() {
        return valorTiquete;
    }

    public void setValorTiquete(DecimalFormat valorTiquete) {
        this.valorTiquete = valorTiquete;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getCodigoTipoTiquete() {
        return codigoTipoTiquete;
    }

    public void setCodigoTipoTiquete(String codigoTipoTiquete) {
        this.codigoTipoTiquete = codigoTipoTiquete;
    }

    public String getCodigoTaquilla() {
        return codigoTaquilla;
    }

    public void setCodigoTaquilla(String codigoTaquilla) {
        this.codigoTaquilla = codigoTaquilla;
    }
}
