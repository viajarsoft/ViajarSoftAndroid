package com.app.viajarsoft.ventatiquetes.dto;

import java.util.List;

/**
 * Created by jose on 27/12/16.
 */

public class BussesAndRoutesDTO {

    private List<RutaDTO> Rutas;
    private List<TipoBusDTO> TiposBus;

    public List<RutaDTO> getRutas() {
        return Rutas;
    }

    public void setRutas(List<RutaDTO> rutas) {
        Rutas = rutas;
    }

    public List<TipoBusDTO> getTiposBus() {
        return TiposBus;
    }

    public void setTiposBus(List<TipoBusDTO> tiposBus) {
        TiposBus = tiposBus;
    }
}
