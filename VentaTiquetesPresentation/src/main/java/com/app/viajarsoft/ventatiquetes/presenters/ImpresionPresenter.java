package com.app.viajarsoft.ventatiquetes.presenters;

import com.app.viajarsoft.ventatiquetes.view.views_activities.IImpresionView;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

/**
 * Created by USUARIO on 19/01/2017.
 */

public class ImpresionPresenter extends BasePresenter<IImpresionView> {

    private ViajeBL viajeBL;

    public ImpresionPresenter(ViajeBL viajeBL) {
        this.viajeBL = viajeBL;
    }
}
