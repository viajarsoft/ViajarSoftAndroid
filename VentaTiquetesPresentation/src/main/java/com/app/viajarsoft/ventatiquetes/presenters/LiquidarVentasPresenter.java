package com.app.viajarsoft.ventatiquetes.presenters;

import com.app.viajarsoft.ventatiquetes.view.views_activities.ILiquidarVentasView;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

/**
 * Created by jose on 20/12/16.
 */

public class LiquidarVentasPresenter extends BasePresenter<ILiquidarVentasView> {

    ViajeBL viajeBL;

    public LiquidarVentasPresenter(ViajeBL viajeBL) {
        this.viajeBL = viajeBL;
    }
}
