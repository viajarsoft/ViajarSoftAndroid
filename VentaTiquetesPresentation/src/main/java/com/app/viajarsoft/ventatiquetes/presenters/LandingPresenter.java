package com.app.viajarsoft.ventatiquetes.presenters;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILandingView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

/**
 * Created by jose on 27/12/16.
 */

public class LandingPresenter extends BasePresenter<ILandingView> {

    private ViajeBL viajeBL;

    public LandingPresenter(ViajeBL viajeBL) {
        this.viajeBL = viajeBL;
    }

    public void validateInternetToGetBussesAndRoutes(String codigoOficina) {
        if (getValidateInternet().isConnected()) {
            createThreadToGetBussesAndRoutes(codigoOficina);
        } else {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        }
    }

    public void createThreadToGetBussesAndRoutes(final String codigoOficina) {
        getView().showProgressDialog(R.string.text_please_wait);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getBussesAndRoutes(codigoOficina);
            }
        });
        thread.start();
    }

    public void getBussesAndRoutes(String codigooficina) {
        try {
            getView().startVenderPasajesActivity(viajeBL.getBussesAndRoutes(codigooficina));
        } catch (RepositoryError repositoryError) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
        } finally {
            getView().dismissProgressDialog();
        }
    }

    public void validateInternetToGetSummaryLiquidation(ResumenLiquidacion resumenLiquidacion) {
        if(getValidateInternet().isConnected()){
            createThreadToGetSummaryLiquidation(resumenLiquidacion);
        }else{
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        }

    }

    public void createThreadToGetSummaryLiquidation(final ResumenLiquidacion resumenLiquidacion) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    VentaPorLiquidar ventaPorLiquidar = viajeBL.getSummaryLiquidation(resumenLiquidacion);
                    getView().setIntentToLiquidarVentas(ventaPorLiquidar);
                } catch (RepositoryError repositoryError) {
                    getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
                }finally {
                    getView().dismissProgressDialog();
                }
            }
        });
        thread.start();
    }
}