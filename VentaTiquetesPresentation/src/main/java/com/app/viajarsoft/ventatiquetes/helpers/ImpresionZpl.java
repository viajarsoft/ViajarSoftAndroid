package com.app.viajarsoft.ventatiquetes.helpers;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.zebra.sdk.comm.BluetoothConnectionInsecure;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.printer.discovery.DiscoveredPrinterBluetooth;

/**
 * Created by USUARIO on 5/01/2017.
 */

public class ImpresionZpl extends AppCompatActivity implements IImpresionZpl{

    @Override
    public void printZpl(final String printZpl, final String mac) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    String bt_printer = mac;

                    // Instantiate insecure connection for given Bluetooth&reg; MAC Address.
                    Connection thePrinterConn = new BluetoothConnectionInsecure(bt_printer);

                    // Initialize
                    if (Looper.myLooper() == null) {
                        Looper.prepare();
                    }

                    // Open the connection - physical connection is established here.
                    thePrinterConn.open();

                    // Send the data to printer as a byte array.
                    thePrinterConn.write(printZpl.getBytes());

                    // Make sure the data got to the printer before closing the connection
                    Thread.sleep(500);

                    // Close the insecure connection to release resources.
                    thePrinterConn.close();

                    Looper.myLooper().quit();
                } catch (Exception e) {
                    // Handle communications error here.
                    e.printStackTrace();
                }
            }
        });
    }
}
