package com.app.viajarsoft.ventatiquetes.dto;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class ViajeDTO {

    private String CodigoTipoBus;
    private String CodigoRuta;
    private String TipoTiquete;
    private String CodigoTaquilla;
    private String CodigoOficina;
    private double ValorTiquete;
    private double ValorSeguro;

    public String getCodigoTipoBus() {
        return CodigoTipoBus;
    }

    public void setCodigoTipoBus(String codigoTipoBus) {
        CodigoTipoBus = codigoTipoBus;
    }

    public String getCodigoRuta() {
        return CodigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        CodigoRuta = codigoRuta;
    }

    public String getTipoTiquete() {
        return TipoTiquete;
    }

    public void setTipoTiquete(String tipoTiquete) {
        TipoTiquete = tipoTiquete;
    }

    public String getCodigoTaquilla() {
        return CodigoTaquilla;
    }

    public void setCodigoTaquilla(String codigoTaquilla) {
        CodigoTaquilla = codigoTaquilla;
    }

    public String getCodigoOficina() {
        return CodigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        CodigoOficina = codigoOficina;
    }

    public double getValorTiquete() {
        return ValorTiquete;
    }

    public void setValorTiquete(double valorTiquete) {
        ValorTiquete = valorTiquete;
    }

    public double getValorSeguro() {
        return ValorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        ValorSeguro = valorSeguro;
    }
}
