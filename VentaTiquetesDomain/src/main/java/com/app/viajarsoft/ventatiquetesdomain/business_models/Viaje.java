package com.app.viajarsoft.ventatiquetesdomain.business_models;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class Viaje {

    private String codigoTipoBus;
    private String codigoRuta;
    private String tipoTiquete;
    private String codigoTaquilla;
    private String codigoOficina;
    private double valorTiquete;
    private double valorSeguro;

    public String getCodigoTipoBus() {
        return codigoTipoBus;
    }

    public void setCodigoTipoBus(String codigoTipoBus) {
        this.codigoTipoBus = codigoTipoBus;
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public void setTipoTiquete(String tipoTiquete) {
        this.tipoTiquete = tipoTiquete;
    }

    public String getTipoTiquete() {
        return tipoTiquete;
    }

    public String getCodigoTaquilla() {
        return codigoTaquilla;
    }

    public void setCodigoTaquilla(String codigoTaquilla) {
        this.codigoTaquilla = codigoTaquilla;
    }

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }

    public double getValorTiquete() {
        return valorTiquete;
    }

    public void setValorTiquete(double valorTiquete) {
        this.valorTiquete = valorTiquete;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
}
