package com.app.viajarsoft.ventatiquetes.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.AppInformation;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.IAppInformation;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;

public class SplashActivity extends AppCompatActivity {

    private TextView splash_tvAppVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadViews();
        loadAppVersion();
        loadPostDelayToStartLogin();
    }

    private void loadPostDelayToStartLogin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startLoginActivity();
            }
        }, IConstants.POST_DELAY_TIME);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadAppVersion() {
        IAppInformation appInformation = new AppInformation(this);
        splash_tvAppVersion.setText(appInformation.getAppVersion());
    }

    private void loadViews() {
        splash_tvAppVersion = (TextView) findViewById(R.id.splash_tvAppVersion);
    }

}