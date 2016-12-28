package com.app.viajarsoft.ventatiquetes.view.views_activities;

import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;

import java.util.List;

/**
 * Created by jose on 20/12/16.
 */

public interface IVenderPasajesView extends IBaseView {

    void loadTicketsSpinnerOnUiThread(List<TipoTiquete> tipoTiquetes);
}
