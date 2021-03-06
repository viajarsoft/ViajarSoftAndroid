package com.app.viajarsoft.ventatiquetes.view.views_activities;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public interface IBaseView {

    void showAlertDialogGeneralInformationOnUiThread(int title, int message);

    void showAlertDialogGeneralInformationOnUiThread(int title, String message);

    void showProgressDIalog(int text);

    void dismissProgressDialog();
}
