package com.app.viajarsoft.ventatiquetes.presenters;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILiquidarVentasView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Liquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

/**
 * Created by jose on 20/12/16.
 */

public class LiquidarVentasPresenter extends BasePresenter<ILiquidarVentasView> {

    ViajeBL viajeBL;

    public LiquidarVentasPresenter(ViajeBL viajeBL) {
        this.viajeBL = viajeBL;
    }

    public void validateInternetToGetLiquidation(Liquidacion liquidacion) {
        if(getValidateInternet().isConnected()){
            createThreadToGetLiquidation(liquidacion);
        }else{
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        }
    }

    public void createThreadToGetLiquidation(final Liquidacion liquidacion) {
       /* Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                     = viajeBL.getLiquidation(liquidacion);
                    getView().printLiquidation();
                } catch (RepositoryError repositoryError) {
                    getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
                }finally {
                    getView().dismissProgressDialog();
                }
            }
        });
        thread.start();
    }*/
    }
}
