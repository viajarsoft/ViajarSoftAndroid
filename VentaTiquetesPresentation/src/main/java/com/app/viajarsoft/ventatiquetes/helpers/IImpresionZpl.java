package com.app.viajarsoft.ventatiquetes.helpers;

import com.zebra.sdk.printer.discovery.DiscoveredPrinterBluetooth;

/**
 * Created by USUARIO on 5/01/2017.
 */

public interface IImpresionZpl  {

    void printZpl(String printZpl, String mac);
}
