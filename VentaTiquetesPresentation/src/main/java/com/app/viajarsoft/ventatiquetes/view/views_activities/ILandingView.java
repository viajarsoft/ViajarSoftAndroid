package com.app.viajarsoft.ventatiquetes.view.views_activities;

import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;

/**
 * Created by jose on 27/12/16.
 */

public interface ILandingView extends IBaseView {
    void startVenderPasajesActivity(BussesAndRoutes bussesAndRoutes);

    void setIntentToLiquidarVentas(VentaPorLiquidar ventaPorLiquidar);
}
