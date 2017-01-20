package com.app.viajarsoft.ventatiquetes.presenters;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.helpers.IImpresionZpl;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionZpl;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILiquidarVentasView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.LiquidacionVentas;
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

    public void validateInternetToGetLiquidation(LiquidacionVentas liquidacionVentas) {
        if(getValidateInternet().isConnected()){
            createThreadToGetLiquidation(liquidacionVentas);
        }else{
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        }
    }

    public void createThreadToGetLiquidation(final LiquidacionVentas liquidacionVentas) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getLiquidation(liquidacionVentas);
            }
        });
        thread.start();
    }

    public void getLiquidation(LiquidacionVentas liquidacionVentas) {

        try {
            ResumenLiquidacionImpresion  resumenLiquidacionImpresion = viajeBL.getLiquidation(liquidacionVentas);
            String addressMac = customSharedPreferences.getString(IConstants.ADDRESSMAC);
            if(addressMac != null && !addressMac.isEmpty()){
                impresionZpl.printZpl(resumenLiquidacionImpresion.getLiquidacion().getZplResumen(), addressMac);
            }else{
             getView().intentToImpresionActivity(resumenLiquidacionImpresion.getLiquidacion().getZplResumen());
            }
        } catch (RepositoryError repositoryError) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
        }finally {
            getView().dismissProgressDialog();
        }
    }
}
