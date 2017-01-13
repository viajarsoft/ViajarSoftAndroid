package com.app.viajarsoft.ventatiquetes.utilities.utils;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public interface IConstants {

    boolean IS_DEBUG = false;

    /**
     * URLs
     */
    String URL_DLLO = "http://apivs.azurewebsites.net/api";

    /**
     * Keys
     */
    String MY_PREFERENCES = "myPreferences";
    String ID_SUSCRIPTION_ONE_SIGNAL = "idSuscripcionOneSignal";
    String USUARIO = "usuario";
    String BUSSES_AND_ROUTES = "bussesAndRoutes";
    String SUMMARY_LIQUIDATION = "summaryLiquidation";
    String CODIGOUSUARIO = "codigoUsuario";
    String IMPRESION = "impresion";

    String TOKEN = "valorToken";
    String ADDRESSMAC = "addressMac";

    /**
     * Constants
     */
    String EMPTY_STRING = "";

    /**
     * Numbers
     */
    long THIRTY = 30;
    String REGULAR_EXPRESSION_CORRECT_EMAIL = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
    /**
     * Error Messages
     */
    String NULL_PARAMETERS = "No se permiten parámetros nulos";
    String EMPTY_PARAMETERS = "No se permiten parámetros vacíos";
    String DEFAUL_ERROR = "Ha ocurrido un error, intentalo nuevamente.";
    int DEFAUL_ERROR_CODE = 0;
    String REQUEST_TIMEOUT_ERROR_MESSAGE = "La solicitud está tardando demasiado. Por favor inténtalo nuevamente.";
    int UNAUTHORIZED_ERROR_CODE = 401;

    int NOT_FOUND_ERROR_CODE = 404;
    String SELECT = "Seleccione...";
    long POST_DELAY_TIME = 2500;
}
