package com.app.viajarsoft.ventatiquetes.dto;


import java.util.List;

/**
 * Created by josetabaresramirez on 28/12/16.
 */

public class ListTipoTiquetesDTO {

    private List<TipoTiqueteDTO> TiposTiquete;

    public List<TipoTiqueteDTO> getTiposTiquete() {
        return TiposTiquete;
    }

    public void setTiposTiquete(List<TipoTiqueteDTO> tiposTiquete) {
        TiposTiquete = tiposTiquete;
    }
}
