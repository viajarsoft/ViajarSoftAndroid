package com.app.viajarsoft.ventatiquetes.security;


import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.presenters.LoginPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.IValidateInternet;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILoginView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Usuario;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.security.ISecurityRepository;
import com.app.viajarsoft.ventatiquetesdomain.security.SecurityBL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by josetabaresramirez on 16/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    ILoginView loginView;

    @Mock
    IValidateInternet validateInternet;

    @Mock
    ISecurityRepository securityRepository;

    @Mock
    ICustomSharedPreferences customSharedPreferences;

    LoginPresenter loginPresenter;

    SecurityBL securityBL;

    @Before
    public void setUp() throws Exception {
        securityBL = Mockito.spy(new SecurityBL(securityRepository));
        loginPresenter = Mockito.spy(new LoginPresenter(securityBL));
        loginPresenter.inject(loginView, validateInternet);
    }

    private UsuarioRequest getUsuarioResponseInstance() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setCorreoElectronico("lcz97@live.com");
        usuarioRequest.setContrasenia("test");
        return usuarioRequest;
    }

    @Test
    public void methodValidateFieldsToLoginWithEmptyEmailShouldShowAlertDialog() {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setCorreoElectronico(IConstants.EMPTY_STRING);
        loginPresenter.validateFieldsToLogin(usuarioRequest);

        verify(loginView).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_email);
        verify(loginView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_password);
        verify(loginPresenter, never()).validateEmail(usuarioRequest);
    }

    @Test
    public void methodValidateFieldsToLoginWithEMptyPasswordShouldShowAlertDialog() {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setContrasenia(IConstants.EMPTY_STRING);
        loginPresenter.validateFieldsToLogin(usuarioRequest);

        verify(loginView).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_password);
        verify(loginView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_email);
        verify(loginPresenter, never()).validateEmail(usuarioRequest);
    }

    @Test
    public void methodValidateFieldsToLoginWithCorrectParametersShouldValidateEmail() {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        loginPresenter.validateFieldsToLogin(usuarioRequest);

        verify(loginPresenter).validateEmail(usuarioRequest);
        verify(loginView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_password);
        verify(loginView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_empty_email);
    }

    @Test
    public void methodValidateEmailWithoutAtAndDomainShouldShowAlertDialog() {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setCorreoElectronico("llll");
        loginPresenter.validateEmail(usuarioRequest);

        verify(loginView).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_incorrect_email);
        verify(loginPresenter, never()).validateInternetToLogin(usuarioRequest);
    }

    @Test
    public void methodValidateEmailWithoutAtShouldShowAlertDialog() {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setCorreoElectronico("llll.com");
        loginPresenter.validateEmail(usuarioRequest);

        verify(loginView).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_incorrect_email);
        verify(loginPresenter, never()).validateInternetToLogin(usuarioRequest);
    }

    @Test
    public void methodValidateEmailWithoutDomainShouldShowAlertDialog() {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setCorreoElectronico("llll@");
        loginPresenter.validateEmail(usuarioRequest);

        verify(loginView).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_incorrect_email);
        verify(loginPresenter, never()).validateInternetToLogin(usuarioRequest);
    }

    @Test
    public void methodValidateEmailWithCorrectEmailShouldCallMethodValidateInternet() {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        loginPresenter.validateEmail(usuarioRequest);
        verify(loginPresenter).validateInternetToLogin(usuarioRequest);
        verify(loginView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_empty_fields, R.string.text_incorrect_email);
    }

    @Test
    public void methodValidateInternetWithoutConnectionShoudlShowAlertDialog() {
        when(validateInternet.isConnected()).thenReturn(false);
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        loginPresenter.validateInternetToLogin(usuarioRequest);
        verify(loginView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        verify(loginPresenter, never()).createThreadToLogin(usuarioRequest);
    }

    @Test
    public void methodValidateInternetWithConnectionShouldCallMethodCreateThreadToLogin() {
        when(validateInternet.isConnected()).thenReturn(true);
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        loginPresenter.validateInternetToLogin(usuarioRequest);
        verify(loginPresenter).createThreadToLogin(usuarioRequest);
        verify(loginView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
    }

    @Test
    public void methodCreateThreadToLoginShouldShowProgressDialog() {
        loginPresenter.createThreadToLogin(getUsuarioResponseInstance());
        verify(loginView).showProgressDIalog(R.string.text_please_wait);
    }

    @Test
    public void methodLoginShouldCalMethodLoginInSecurityBL() throws RepositoryError {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        Usuario usuario = new Usuario();
        usuario.setToken("sgg65456");
        when(securityRepository.login(usuarioRequest)).thenReturn(usuario);
        loginPresenter.login(usuarioRequest);
        verify(securityBL).login(usuarioRequest);
    }

    @Test
    public void methodLoginShouldCallMethodSaveToken() throws RepositoryError {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        Usuario usuario = new Usuario();
        usuario.setToken("sgg65456");
        when(securityRepository.login(usuarioRequest)).thenReturn(usuario);
        loginPresenter.login(usuarioRequest);
        verify(loginView).saveToken(usuario.getToken());
    }

    @Test
    public void methodLoginShouldCallMethodStartLanding() throws RepositoryError {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        Usuario usuario = new Usuario();
        usuario.setToken("sgg65456");
        when(securityRepository.login(usuarioRequest)).thenReturn(usuario);
        loginPresenter.login(usuarioRequest);
        verify(loginView).startLanding(usuario);
    }

    @Test
    public void methodLoginShouldShowAnAlertDialogWhenReturnAnException() throws RepositoryError {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        RepositoryError repositoryError = new RepositoryError(IConstants.DEFAUL_ERROR);
        repositoryError.setIdError(0);
        when(securityRepository.login(usuarioRequest)).thenThrow(repositoryError);
        loginPresenter.login(usuarioRequest);
        verify(loginView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
    }

    @Test
    public void methodLoginShouldCallMethodHideProgressDialog() throws RepositoryError {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        Usuario usuario = new Usuario();
        usuario.setToken("sgg65456");
        when(securityRepository.login(usuarioRequest)).thenReturn(usuario);
        loginPresenter.login(usuarioRequest);
        verify(loginView).dismissProgressDialog();
    }
}
