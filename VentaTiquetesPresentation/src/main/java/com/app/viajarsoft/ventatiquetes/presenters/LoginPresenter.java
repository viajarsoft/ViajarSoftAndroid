package com.app.viajarsoft.ventatiquetes.presenters;


import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILoginView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.app.viajarsoft.ventatiquetesdomain.security.SecurityBL;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private SecurityBL securityBL;

    public LoginPresenter(SecurityBL securityBL) {
        this.securityBL = securityBL;
    }

    public void validateFieldsToLogin(UsuarioRequest usuarioRequest) {
        if (usuarioRequest.getUsuario().isEmpty() || usuarioRequest.getContrasenia().isEmpty()) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_fields);
        } else {
            validateInternetToLogin(usuarioRequest);
        }
    }


    public void validateInternetToLogin(UsuarioRequest usuarioRequest) {
        if (getValidateInternet().isConnected()) {
            createThreadToLogin(usuarioRequest);
        } else {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        }
    }

    public void createThreadToLogin(final UsuarioRequest usuarioRequest) {
        getView().showProgressDialog(R.string.text_please_wait);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                login(usuarioRequest);
            }
        });
        thread.start();
    }

    public void login(UsuarioRequest usuarioRequest) {
        try {
            UsuarioResponse usuarioResponse = securityBL.login(usuarioRequest);
            getView().saveToken(usuarioResponse.getToken());
            getView().startLanding(usuarioResponse);
        } catch (RepositoryError repositoryError) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
        } finally {
            getView().dismissProgressDialog();
        }
    }
}
