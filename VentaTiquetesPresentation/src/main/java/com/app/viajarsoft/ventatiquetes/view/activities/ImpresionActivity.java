package com.app.viajarsoft.ventatiquetes.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.Toast;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.helpers.IImpresionZpl;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionHelper;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionZpl;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.adapters.ImpresionAdapter;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ImpresionClick;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.discovery.BluetoothDiscoverer;
import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import com.zebra.sdk.printer.discovery.DiscoveredPrinterBluetooth;
import com.zebra.sdk.printer.discovery.DiscoveryHandler;

import java.util.List;

/**
 * Created by USUARIO on 4/01/2017.
 */

public class ImpresionActivity extends AppCompatActivity implements DiscoveryHandler {


    protected List<String> discoveredPrinters = null;
    private ImpresionAdapter impresionAdapter;
    private RecyclerView impresion_rvList;
    private String zlpPrint;
    private boolean comenzar;
    private ProgressDialog progressDialog;
    private ICustomSharedPreferences customSharedPreferences;
    private IImpresionZpl impresionZpl;

    public ImpresionActivity() {
        super();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_impresion);
        setProgressBarIndeterminateVisibility(true);
        asignarDatos();
        getDataIntent();
        this.customSharedPreferences = new CustomSharedPreferences(this);
        impresion_rvList = (RecyclerView) findViewById(R.id.impresion_rvList);
        impresion_rvList.setHasFixedSize(true);
        impresion_rvList.setAdapter(impresionAdapter);
        comenzarBusqueda();
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
                    new ImpresionHelper(ImpresionActivity.this).showErrorDialogOnGuiThread(e.getMessage());
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
                impresionZpl.printZpl(zlpPrint, customSharedPreferences.getString(IConstants.ADDRESSMAC));
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
        new ImpresionHelper(this).showErrorDialogOnGuiThread(error);
    }
}