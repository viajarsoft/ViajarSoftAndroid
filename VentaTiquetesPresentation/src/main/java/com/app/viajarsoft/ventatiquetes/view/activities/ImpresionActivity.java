package com.app.viajarsoft.ventatiquetes.view.activities;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.Toast;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionHelper;
import com.app.viajarsoft.ventatiquetes.view.adapters.ImpresionAdapter;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.discovery.BluetoothDiscoverer;
import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import com.zebra.sdk.printer.discovery.DiscoveryHandler;

import java.util.List;

/**
 * Created by USUARIO on 4/01/2017.
 */

public class ImpresionActivity extends AppCompatActivity implements DiscoveryHandler {


    protected List<String> discoveredPrinters = null;
    private ImpresionAdapter impresionAdapter;
    private RecyclerView impresion_rvList;


    public ImpresionActivity() {
        super();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        setProgressBarIndeterminateVisibility(true);

        impresion_rvList = (RecyclerView) findViewById(R.id.impresion_rvList);

        impresionAdapter = new ImpresionAdapter();
        impresion_rvList.setAdapter(impresionAdapter);


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

    @Override
    public void foundPrinter(final DiscoveredPrinter discoveredPrinter) {
        runOnUiThread(new Runnable() {
            public void run() {
                impresionAdapter.addPrinterItem( discoveredPrinter);
                impresionAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void discoveryFinished() {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ImpresionActivity.this, " Se han encontrado " + impresionAdapter.getItemCount() + " impresoras", Toast.LENGTH_SHORT).show();
                setProgressBarIndeterminateVisibility(false);
            }
        });
    }

    @Override
    public void discoveryError(String s) {
        new ImpresionHelper(this).showErrorDialogOnGuiThread(s);

    }
}