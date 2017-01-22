package com.app.viajarsoft.ventatiquetes.presenters;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILandingView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenVentasPorLiquidar;
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
            if (repositoryError.getIdError() == IConstants.UNAUTHORIZED_ERROR_CODE) {
                getView().showAlertDialogUnauthorizedOnUiThread(repositoryError.getMessage());
            } else {
                getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
            }
        } finally {
            getView().dismissProgressDialog();
        }
    }

    public void validateInternetToGetSummaryLiquidation(ResumenLiquidacion resumenLiquidacion) {
        if (getValidateInternet().isConnected()) {
            createThreadToGetSummaryLiquidation(resumenLiquidacion);
        } else {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        }

    }

    public void createThreadToGetSummaryLiquidation(final ResumenLiquidacion resumenLiquidacion) {
        getView().showProgressDialog(R.string.text_please_wait);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getSummaryLiquidation(resumenLiquidacion);
            }
        });
        thread.start();
    }

    public void getSummaryLiquidation(ResumenLiquidacion resumenLiquidacion) {
        try {
            ResumenVentasPorLiquidar resumenVentasPorLiquidar = viajeBL.getSummaryLiquidation(resumenLiquidacion);
            getView().setIntentToLiquidarVentas(resumenVentasPorLiquidar.getVentaPorLiquidar());
        } catch (RepositoryError repositoryError) {
            if (repositoryError.getIdError() == IConstants.UNAUTHORIZED_ERROR_CODE) {
                getView().showAlertDialogUnauthorizedOnUiThread(repositoryError.getMessage());
            } else {
                getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
            }
        } finally {
            getView().dismissProgressDialog();
        }
    }
}