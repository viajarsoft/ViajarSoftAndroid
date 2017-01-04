package com.app.viajarsoft.ventatiquetes.dto;

import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;

/**
 * Created by USUARIO on 3/01/2017.
 */

public class ResumenVentasPorLiquidarDTO {

    private VentaPorLiquidarDTO VentaPorLiquidar;

    public VentaPorLiquidarDTO getVentaPorLiquidar() {
        return VentaPorLiquidar;
    }

    public void setVentaPorLiquidar(VentaPorLiquidarDTO ventaPorLiquidar) {
        VentaPorLiquidar = ventaPorLiquidar;
    }
}
