package com.app.viajarsoft.ventatiquetes.view.views_activities;

import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public interface ILoginView extends IBaseView {

    void saveToken(String token);

    void startLanding(UsuarioResponse usuarioResponse);
}
