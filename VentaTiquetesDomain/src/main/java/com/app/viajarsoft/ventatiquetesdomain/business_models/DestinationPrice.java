package com.app.viajarsoft.ventatiquetesdomain.business_models;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class DestinationPrice {

    private String destino;
    private int valorTiquete;
    private int valorSeguro;

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getValorTiquete() {
        return valorTiquete;
    }

    public void setValorTiquete(int valorTiquete) {
        this.valorTiquete = valorTiquete;
    }

    public int getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(int valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
}
