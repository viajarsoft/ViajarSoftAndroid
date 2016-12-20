package com.app.viajarsoft.ventatiquetes.utilities.helpers;


import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;

import java.util.Calendar;


/**
 * Created by JoseTabares on 13/07/16.
 */
public class Validations {

    /**
     * Valida un parametro, si es nulo retorna una excepción.
     *
     * @param data data
     * @param <T>  Tipo de dato
     */
    public static <T> void validateNullParameter(T data) {
        if (data == null) {
            throw new IllegalArgumentException(IConstants.NULL_PARAMETERS);
        }
    }

    /**
     * Valida varios parametros, si alguno es nulo retorna una excepción.
     *
     * @param data data
     * @param <T>  Tipo de dato
     */
    @SafeVarargs
    public static <T> void validateNullParameter(T... data) {
        for (T oneData : data) {
            if (oneData == null) {
                throw new IllegalArgumentException(IConstants.NULL_PARAMETERS);
            }
        }
    }

    /**
     * Valida un parametro, si está vacío retorna una excepción.
     *
     * @param string texto
     */
    public static void validateEmptyParameter(String string) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException(IConstants.EMPTY_PARAMETERS);
        }
    }

    /**
     * Valida varios parametros, si alguno está vacío retorna una excepción.
     *
     * @param strings texto
     */
    public static void validateEmptyParameter(String... strings) {
        for (String string : strings) {
            if (string.isEmpty()) {
                throw new IllegalArgumentException(IConstants.EMPTY_PARAMETERS);
            }
        }
    }

    /**
     * Valida si dos fechas son iguales.
     *
     * @param calendarToday     calendarToday.
     * @param calendarToCompare calendarToCompare.
     * @return True/False.
     */
    public static boolean areEqualsDates(Calendar calendarToday, Calendar calendarToCompare) {
        if (calendarToday.get(Calendar.YEAR) != calendarToCompare.get(Calendar.YEAR)) {
            return false;
        }

        if (calendarToday.get(Calendar.DAY_OF_MONTH) != calendarToCompare.get(Calendar.DAY_OF_MONTH)) {
            return false;
        }

        if (calendarToday.get(Calendar.MONTH) != calendarToCompare.get(Calendar.MONTH)) {
            return false;
        }

        return true;
    }
}
