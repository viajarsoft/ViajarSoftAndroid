package com.app.viajarsoft.ventatiquetes.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.dependency_injection.DomainModule;
import com.app.viajarsoft.ventatiquetes.presenters.LoginPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILoginView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;


public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    private EditText login_etCorreoElectronico;
    private EditText login_etContrasenia;
    private Button login_btnIngresarAhora;

    private ICustomSharedPreferences customSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.customSharedPreferences = new CustomSharedPreferences(this);
        setPresenter(new LoginPresenter(DomainModule.getSecurityBLInstance(customSharedPreferences)));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
        loadViews();
    }

    private void loadViews() {
        login_etCorreoElectronico = (EditText) findViewById(R.id.login_etCorreoElectronico);
        login_etContrasenia = (EditText) findViewById(R.id.login_etContrasenia);
        login_btnIngresarAhora = (Button) findViewById(R.id.login_btnIngresarAhora);

        loadListenerToTheControls();
    }

    private void loadListenerToTheControls() {
        loadOnClickListenerToTheControlLoginBtnIngresarAhora();
    }

    private void loadOnClickListenerToTheControlLoginBtnIngresarAhora() {
        login_btnIngresarAhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareDataToLogin();
            }
        });
    }

    private void prepareDataToLogin() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setUsuario(login_etCorreoElectronico.getText().toString().trim());
        usuarioRequest.setContrasenia(login_etContrasenia.getText().toString().trim());
        getPresenter().validateFieldsToLogin(usuarioRequest);
    }

    @Override
    public void saveToken(String token) {
        customSharedPreferences.addString(IConstants.TOKEN, token);
    }

    @Override
    public void startLanding(UsuarioResponse usuarioResponse, String usuario) {
        Intent intent = new Intent(LoginActivity.this, LandingActivity.class);
        intent.putExtra(IConstants.USUARIO, usuarioResponse);
        intent.putExtra(IConstants.CODIGOUSUARIO,usuario);
        startActivity(intent);
    }
}
