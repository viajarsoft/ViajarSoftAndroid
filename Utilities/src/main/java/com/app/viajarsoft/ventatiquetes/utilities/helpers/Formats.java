package com.app.viajarsoft.ventatiquetes.utilities.helpers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by jose on 28/12/16.
 */

public class Formats {

    public static String getCurrency(double value) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###.##", decimalFormatSymbols);
        return decimalFormat.format(value);
    }
}
