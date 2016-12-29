package com.app.viajarsoft.ventatiquetes.dto;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class DestinationPriceDTO {

    private String Destino;
    private int ValorTiquete;
    private int ValorSeguro;

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public int getValorTiquete() {
        return ValorTiquete;
    }

    public void setValorTiquete(int valorTiquete) {
        ValorTiquete = valorTiquete;
    }

    public int getValorSeguro() {
        return ValorSeguro;
    }

    public void setValorSeguro(int valorSeguro) {
        ValorSeguro = valorSeguro;
    }
}
