package com.app.viajarsoft.ventatiquetes.presenters;


import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILoginView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Usuario;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
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
        if (usuarioRequest.getCorreoElectronico().isEmpty()) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_email);
            return;
        }
        if (usuarioRequest.getContrasenia().isEmpty()) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_password);
            return;
        }
        validateEmail(usuarioRequest);
    }


    public void validateEmail(UsuarioRequest usuarioRequest) {
        if (usuarioRequest.getCorreoElectronico().matches(IConstants.REGULAR_EXPRESSION_CORRECT_EMAIL)) {
            validateInternetToLogin(usuarioRequest);
        } else {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_incorrect_email);
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
        getView().showProgressDIalog(R.string.text_please_wait);
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
            Usuario usuario = securityBL.login(usuarioRequest);
            getView().saveToken(usuario.getToken());
            getView().startLanding(usuario);
        } catch (RepositoryError repositoryError) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
        } finally {
            getView().dismissProgressDialog();
        }
    }
}
