package com.app.viajarsoft.ventatiquetesdomain.business_models;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class DestinationPrice {

    private String destino;
    private double valorTiquete;
    private double valorSeguro;

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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
