package com.app.viajarsoft.ventatiquetes.presenters;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.helpers.IImpresionZpl;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionZpl;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILiquidarVentasView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Liquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacionImpresion;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

/**
 * Created by jose on 20/12/16.
 */

public class LiquidarVentasPresenter extends BasePresenter<ILiquidarVentasView> {

    ViajeBL viajeBL;
    IImpresionZpl impresionZpl;
    ICustomSharedPreferences customSharedPreferences;


    public LiquidarVentasPresenter(ViajeBL viajeBL) {
        this.viajeBL = viajeBL;
        impresionZpl = new ImpresionZpl();
    }

    public void validateInternetToGetLiquidation(Liquidacion liquidacion) {
        if(getValidateInternet().isConnected()){
            createThreadToGetLiquidation(liquidacion);
        }else{
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        }
    }

    public void createThreadToGetLiquidation(final Liquidacion liquidacion) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getLiquidation(liquidacion);
            }
        });
        thread.start();
    }

    public void getLiquidation(Liquidacion liquidacion) {

        try {
            ResumenLiquidacionImpresion  resumenLiquidacionImpresion = viajeBL.getLiquidation(liquidacion);
            String addressMac = customSharedPreferences.getString(IConstants.ADDRESSMAC);
            if(addressMac != null && !addressMac.isEmpty()){
                impresionZpl.printZpl(resumenLiquidacionImpresion.getZplResumen(), addressMac);
            }else{
             getView().intentToImpresionActivity(resumenLiquidacionImpresion.getZplResumen());
            }
        } catch (RepositoryError repositoryError) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
        }finally {
            getView().dismissProgressDialog();
        }
    }
}
