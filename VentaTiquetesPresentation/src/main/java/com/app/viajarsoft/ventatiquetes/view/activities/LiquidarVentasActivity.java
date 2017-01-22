package com.app.viajarsoft.ventatiquetes.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.dependency_injection.DomainModule;
import com.app.viajarsoft.ventatiquetes.helpers.IImpresionZpl;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionZpl;
import com.app.viajarsoft.ventatiquetes.presenters.LiquidarVentasPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILiquidarVentasView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.LiquidacionVentas;
import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;

public class LiquidarVentasActivity extends BaseActivity<LiquidarVentasPresenter> implements ILiquidarVentasView {

    private TextView liquidar_tvUsuario;
    private TextView liquidar_tvPasajesVendidos;
    private TextView liquidar_tvValorTotal;
    private Button liquidar_btnLiquidar;
    private String usuario;
    private VentaPorLiquidar ventaPorLiquidar;
    private IImpresionZpl impresionZpl;
    private ICustomSharedPreferences customSharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquidar_ventas);
        loadToolbar();
        setPresenter(new LiquidarVentasPresenter(DomainModule.getViajeBLInstance(new CustomSharedPreferences(this))));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
        this.usuario = getIntent().getStringExtra(IConstants.CODIGOUSUARIO);
        this.ventaPorLiquidar = (VentaPorLiquidar) getIntent().getSerializableExtra(IConstants.SUMMARY_LIQUIDATION);
        customSharedPreferences = new CustomSharedPreferences(LiquidarVentasActivity.this);
        impresionZpl = new ImpresionZpl();
        loadViews();
        loadListener();
        setInfo();
    }

    private void loadListener() {

        liquidar_btnLiquidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiquidacionVentas liquidacionVentas = new LiquidacionVentas();
                liquidacionVentas.setCodigoOficina(ventaPorLiquidar.getCodigoOficina());
                liquidacionVentas.setCodigoTaquilla(ventaPorLiquidar.getCodigoTaquilla());
                liquidacionVentas.setFechaVenta(ventaPorLiquidar.getFechaVenta());
                liquidacionVentas.setCodigoUsuario(usuario);
                liquidacionVentas.setTipoVenta("1024");
                getPresenter().validateInternetToGetLiquidation(liquidacionVentas);
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

    private void setInfo(){
        liquidar_tvUsuario.setText(ventaPorLiquidar.getNombreTaquilla());
        liquidar_tvPasajesVendidos.setText(ventaPorLiquidar.getCantidad() + "");
        liquidar_tvValorTotal.setText(ventaPorLiquidar.getValorTiquete().toString());
    }

    @Override
    public void intentToImpresionActivity(String zplResumen) {
        String addressMac = customSharedPreferences.getString(IConstants.ADDRESSMAC);
        if(addressMac != null && !addressMac.isEmpty()) {
            impresionZpl.printZpl(zplResumen, addressMac);
        }else{
            Intent intent = new Intent(LiquidarVentasActivity.this, ImpresionActivity.class);
            intent.putExtra(IConstants.IMPRESION, zplResumen);
            startActivity(intent);
        }
    }
}
