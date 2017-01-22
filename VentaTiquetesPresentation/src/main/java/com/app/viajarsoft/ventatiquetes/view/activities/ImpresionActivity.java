package com.app.viajarsoft.ventatiquetes.view.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.dependency_injection.DomainModule;
import com.app.viajarsoft.ventatiquetes.helpers.IImpresionZpl;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionHelper;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionZpl;
import com.app.viajarsoft.ventatiquetes.presenters.ImpresionPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.adapters.ImpresionAdapter;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IImpresionView;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ImpresionClick;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.discovery.BluetoothDiscoverer;
import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import com.zebra.sdk.printer.discovery.DiscoveredPrinterBluetooth;
import com.zebra.sdk.printer.discovery.DiscoveryHandler;

/**
 * Created by USUARIO on 4/01/2017.
 */

public class ImpresionActivity extends BaseActivity<ImpresionPresenter> implements DiscoveryHandler , IImpresionView {


    private ImpresionAdapter impresionAdapter;
    private RecyclerView impresion_rvList;
    private String zlpPrint;
    private ProgressDialog progressDialog;
    private RecyclerView.LayoutManager layoutManager;
    private ICustomSharedPreferences customSharedPreferences;
    private IImpresionZpl impresionZpl;
    private Button impresion_btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impresion);
        setPresenter(new ImpresionPresenter(DomainModule.getViajeBLInstance(new CustomSharedPreferences(this))));
        getPresenter().inject(this, getValidateInternet());
        getDataIntent();
        this.customSharedPreferences = new CustomSharedPreferences(this);
        this.impresionZpl = new ImpresionZpl();
        asignarDatos();
        impresion_rvList = (RecyclerView) findViewById(R.id.impresion_rvList);
        layoutManager = new LinearLayoutManager(this);
        impresion_rvList.setLayoutManager(layoutManager);
        //impresion_rvList.setHasFixedSize(true);
        impresion_rvList.setAdapter(impresionAdapter);
        loadToolbar();
        loadListener();
        habilitarBluetooth();
        permissions();
    }

    private void loadListener() {
        impresion_btnRegresar = (Button) findViewById(R.id.impresion_btnRegresar);
        impresion_btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void permissions() {
        int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        comenzarBusqueda();
    }


    private void loadToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.text_buscar_impresora));
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void comenzarBusqueda() {
        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.text_buscando_impresora));
        impresionAdapter.limpiarLista();
        impresionAdapter.notifyDataSetChanged();
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                try {
                    BluetoothDiscoverer.findPrinters(ImpresionActivity.this, ImpresionActivity.this);
                } catch (ConnectionException e) {
                    showAlertDialogGeneralInformationOnUiThread(R.string.title_error, R.string.text_error);
                } finally {
                    Looper.myLooper().quit();
                }
            }
        }).start();
    }


    private void asignarDatos() {
        impresionAdapter = new ImpresionAdapter(new ImpresionClick() {
            @Override
            public void onItemClick(DiscoveredPrinterBluetooth item) {
                customSharedPreferences.addString(IConstants.ADDRESSMAC, item.address);
                if(zlpPrint != null){
                    impresionZpl.printZpl(zlpPrint, customSharedPreferences.getString(IConstants.ADDRESSMAC));
                }else{
                    onBackPressed();
                    Toast.makeText(ImpresionActivity.this, R.string.text_item_impresion,Toast.LENGTH_LONG);
                }

            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(IConstants.IMPRESION) != null) {
            zlpPrint = intent.getStringExtra(IConstants.IMPRESION);
        }
    }

    @Override
    public void foundPrinter(final DiscoveredPrinter discoveredPrinter) {
        progressDialog.dismiss();
        impresionAdapter.addPrinterItem(discoveredPrinter);
        impresionAdapter.notifyDataSetChanged();
    }

    @Override
    public void discoveryFinished() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (impresionAdapter.getItemCount() == 0) {
            Toast.makeText(ImpresionActivity.this, " Se han encontrado " + impresionAdapter.getItemCount() + " impresoras", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void discoveryError(String error) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        new ImpresionHelper(this).showErrorDialogOnGuiThread(error);
    }

    private void habilitarBluetooth(){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.enable();
    }
}