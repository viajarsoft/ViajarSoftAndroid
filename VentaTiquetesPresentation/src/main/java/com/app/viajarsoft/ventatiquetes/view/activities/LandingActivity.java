package com.app.viajarsoft.ventatiquetes.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.viajarsoft.ventatiquetes.R;

public class LandingActivity extends BaseActivity {

    private CardView landing_cvVenderPasajes;
    private CardView landing_cvLiquidarVentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        loadToolbar();
        loadViews();
    }

    private void loadViews() {
        landing_cvVenderPasajes = (CardView) findViewById(R.id.landing_cvVenderPasajes);
        landing_cvLiquidarVentas = (CardView) findViewById(R.id.landing_cvLiquidarVentas);

        loadListenerToTheControls();
    }

    private void loadListenerToTheControls() {
        loadOnClickToTheControlLandingCvVenderPasajes();
        loadOnClickToTheControlLandingCvLiquidarVentas();
    }

    private void loadOnClickToTheControlLandingCvVenderPasajes() {
        landing_cvVenderPasajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VenderPasajesActivity.class);
            }
        });
    }

    private void loadOnClickToTheControlLandingCvLiquidarVentas() {
        landing_cvLiquidarVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LiquidarVentasActivity.class);
            }
        });
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(LandingActivity.this, clazz);
        startActivity(intent);
    }

    private void loadToolbar() {
        Toolbar toolbar = (Toolbar)this.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_viajar_soft);
        setSupportActionBar(toolbar);
    }
}
