package com.app.viajarsoft.ventatiquetes.utilities.custom_controls;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by josetabaresramirez on 18/11/16.
 */

public class CustomAlertDialog {

    private Activity activity;

    public CustomAlertDialog(Activity activity) {
        this.activity = activity;
    }

    public void showAlertDialog(int title, int message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton) {
        showAlertDialog(title, activity.getResources().getString(message), cancelable, textPositiveButton, onClickListenerPositiveButton, 0, null);
    }

    public void showAlertDialog(int title, String message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton) {
        showAlertDialog(title, message, cancelable, textPositiveButton, onClickListenerPositiveButton, 0, null);
    }

    public void showAlertDialog(int title, int message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton) {
        showAlertDialog(title, activity.getResources().getString(message), cancelable, textPositiveButton, onClickListenerPositiveButton, textNegativeButton, onClickListenerNegativeButton);
    }

    public void showAlertDialog(int title, String message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        if (onClickListenerPositiveButton != null) {
            builder.setPositiveButton(textPositiveButton, onClickListenerPositiveButton);
        }
        if (onClickListenerNegativeButton != null) {
            builder.setNegativeButton(textNegativeButton, onClickListenerNegativeButton);
        }
        builder.show();
    }
}
