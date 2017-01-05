package com.app.viajarsoft.ventatiquetes.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.dependency_injection.DomainModule;
import com.app.viajarsoft.ventatiquetes.presenters.LandingPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILandingView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;

public class LandingActivity extends BaseActivity<LandingPresenter> implements ILandingView{

    private CardView landing_cvVenderPasajes;
    private CardView landing_cvLiquidarVentas;
    private CardView landing_cvImpresora;

    private UsuarioResponse usuarioResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        setPresenter(new LandingPresenter(DomainModule.getViajeBLInstance(new CustomSharedPreferences(this))));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
        this.usuarioResponse = (UsuarioResponse) getIntent().getSerializableExtra(IConstants.USUARIO);
        loadToolbar();
        loadViews();
    }

    private void loadViews() {
        landing_cvVenderPasajes = (CardView) findViewById(R.id.landing_cvVenderPasajes);
        landing_cvLiquidarVentas = (CardView) findViewById(R.id.landing_cvLiquidarVentas);
        landing_cvImpresora = (CardView) findViewById(R.id.landing_cvImpresora);
        loadListenerToTheControls();
    }

    private void loadListenerToTheControls() {
        loadOnClickToTheControlLandingCvVenderPasajes();
        loadOnClickToTheControlLandingCvLiquidarVentas();
        loadOnClickToTheControlLandingCvImpresora();
    }

    private void loadOnClickToTheControlLandingCvImpresora() {
        landing_cvImpresora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingActivity.this, ImpresionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadOnClickToTheControlLandingCvVenderPasajes() {
        landing_cvVenderPasajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().validateInternetToGetBussesAndRoutes(usuarioResponse.getCodigoOficina());
            }
        });
    }

    private void loadOnClickToTheControlLandingCvLiquidarVentas() {
        landing_cvLiquidarVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResumenLiquidacion resumenLiquidacion = new ResumenLiquidacion();
                resumenLiquidacion.setCodigoOficina(usuarioResponse.getCodigoOficina());
                resumenLiquidacion.setCodigoTaquilla(usuarioResponse.getCodigoTaquilla());
                getPresenter().validateInternetToGetSummaryLiquidation(resumenLiquidacion);
            }
        });
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(LandingActivity.this, clazz);
        intent.putExtra(IConstants.USUARIO, this.usuarioResponse);
        startActivity(intent);
    }

    private void loadToolbar() {
        Toolbar toolbar = (Toolbar)this.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_viajar_soft);
        setSupportActionBar(toolbar);
    }

    @Override
    public void startVenderPasajesActivity(final BussesAndRoutes bussesAndRoutes) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LandingActivity.this, VenderPasajesActivity.class);
                intent.putExtra(IConstants.USUARIO, usuarioResponse);
                intent.putExtra(IConstants.BUSSES_AND_ROUTES, bussesAndRoutes);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setIntentToLiquidarVentas(final VentaPorLiquidar ventaPorLiquidar) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LandingActivity.this, LiquidarVentasActivity.class);
                intent.putExtra(IConstants.USUARIO, usuarioResponse);
                intent.putExtra(IConstants.SUMMARY_LIQUIDATION, ventaPorLiquidar);
                startActivity(intent);
            }
        });
    }
}
