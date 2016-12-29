package com.app.viajarsoft.ventatiquetes.view.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.presenters.LiquidarVentasPresenter;

public class LiquidarVentasActivity extends BaseActivity<LiquidarVentasPresenter> {

    private TextView liquidar_tvUsuario;
    private TextView liquidar_tvPasajesVendidos;
    private TextView liquidar_tvValorTotal;
    private Button liquidar_btnLiquidar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquidar_ventas);
        loadToolbar();
        loadViews();
        loadListener();
    }

    private void loadListener() {

        liquidar_btnLiquidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: logica para imprimir factura
            }
        });
    }

    private void loadViews() {

        liquidar_tvUsuario = (TextView) findViewById(R.id.liquidar_tvUsuario);
        liquidar_tvPasajesVendidos = (TextView) findViewById(R.id.liquidar_tvPasajesVendidos);
        liquidar_tvValorTotal = (TextView) findViewById(R.id.liquidar_tvValorTotal);
        liquidar_btnLiquidar = (Button) findViewById(R.id.liquidar_btnLiquidar);
    }



    private void loadToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_liquidar_ventas);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
