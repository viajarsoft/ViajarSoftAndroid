package com.app.viajarsoft.ventatiquetes.view.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.utilities.custom_controls.CustomAlertDialog;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.IValidateInternet;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ValidateInternet;
import com.app.viajarsoft.ventatiquetes.presenters.BasePresenter;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IBaseView;


/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    private T presenter;
    private IValidateInternet validateInternet;
    private ProgressDialog progressDialog;
    private CustomAlertDialog customAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.validateInternet = new ValidateInternet(this);
        this.customAlertDialog = new CustomAlertDialog(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IConstants.DEFAUl_REQUEST_CODE) {
            switch (resultCode) {
                case IConstants.UNAUTHORIZED_ERROR_CODE:
                    setResult(IConstants.UNAUTHORIZED_ERROR_CODE);
                    finish();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showAlertDialogGeneralInformationOnUiThread(final int title, final int message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialogGeneralInformation(title, message);
            }
        });
    }

    @Override
    public void showAlertDialogGeneralInformationOnUiThread(final int title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialogGeneralInformation(title, message);
            }
        });
    }

    @Override
    public void showProgressDialog(int text) {
        this.progressDialog.setMessage(getResources().getString(text));
        this.progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        this.progressDialog.dismiss();
    }

    @Override
    public void showAlertDialogUnauthorizedOnUiThread(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialogUnauthorized(message);
            }
        });
    }


    private void showAlertDialogUnauthorized(String message) {
        customAlertDialog.showAlertDialog(R.string.title_appreciated_user, message, false, R.string.text_aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        setResult(IConstants.UNAUTHORIZED_ERROR_CODE);
        finish();
    }

    public void createProgressDialog() {
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setCancelable(false);
    }

    private void showAlertDialogGeneralInformation(int title, int message) {
        customAlertDialog.showAlertDialog(title, message, false, R.string.text_aceptar, getDefaulPositiveButtonOnClickListener());
    }

    private void showAlertDialogGeneralInformation(int title, String message) {
        customAlertDialog.showAlertDialog(title, message, false, R.string.text_aceptar, getDefaulPositiveButtonOnClickListener());
    }

    private DialogInterface.OnClickListener getDefaulPositiveButtonOnClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }

    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    public IValidateInternet getValidateInternet() {
        return validateInternet;
    }

    public void setValidateInternet(IValidateInternet validateInternet) {
        this.validateInternet = validateInternet;
    }
}
