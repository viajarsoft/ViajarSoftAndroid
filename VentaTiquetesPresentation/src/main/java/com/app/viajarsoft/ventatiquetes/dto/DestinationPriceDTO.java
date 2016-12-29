package com.app.viajarsoft.ventatiquetes.dto;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class DestinationPriceDTO {

    private String Destino;
    private double ValorTiquete;
    private double ValorSeguro;

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
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
