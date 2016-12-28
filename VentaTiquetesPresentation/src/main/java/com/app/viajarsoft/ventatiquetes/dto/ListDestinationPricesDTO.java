package com.app.viajarsoft.ventatiquetes.dto;


import java.util.List;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class ListDestinationPricesDTO {

    private List<DestinationPriceDTO> PreciosDestino;

    public List<DestinationPriceDTO> getPreciosDestino() {
        return PreciosDestino;
    }

    public void setPreciosDestino(List<DestinationPriceDTO> preciosDestino) {
        PreciosDestino = preciosDestino;
    }
}
